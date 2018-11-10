package recordstore.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Artist;
import recordstore.exception.RecordStoreException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.sql.DataSource;

/**
 * An implementation of {@link ArtistDao} that persists artists in RDBMS.
 *
 */
public class DbArtistDao implements ArtistDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbArtistDao.class);
    private final DataSource dataSource;

    /**
     * Luo instanssin {@link DbArtistDao} joka hyödyntää tarjottua <code>dataSource</code>
     * säilömiseen ja artistin tietojen hakemiseen
     *
     * @param dataSource a non-null dataSource.
     */
    public DbArtistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return a lazily populated stream of artists. Note the stream returned must be closed to
     *     free all the acquired resources. The stream keeps an open connection to the database till
     *     it is complete or is closed manually.
     */
    @Override
    public Stream<Artist> getAll() {
        Connection connection;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ARTISTS"); // NOSONAR
            ResultSet resultSet = statement.executeQuery(); // NOSONAR
            return StreamSupport.stream(new Spliterators.AbstractSpliterator<Artist>(Long.MAX_VALUE,
                    Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super Artist> action) {
                    try {
                        if (!resultSet.next()) {
                            return false;
                        }
                        action.accept(createArtist(resultSet));
                        return true;
                    } catch (SQLException e) {
                        throw new RuntimeException(e); // NOSONAR
                    }
                }
            }, false).onClose(() -> mutedClose(connection, statement, resultSet));
        } catch (SQLException e) {
            throw new RecordStoreException(e.getMessage(), e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try{
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.info("Poikkeus heitetty " + e.getMessage());
        }
    }

    private Artist createArtist(ResultSet resultSet) throws SQLException {
        return new Artist(resultSet.getInt("ID"),
                resultSet.getString("name"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Artist> getById(int id) throws SQLException {
        ResultSet resultSet = null;

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM ARTISTS WHERE ID = ?")) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createArtist(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new RecordStoreException(ex.getMessage(), ex);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(Artist artist) throws SQLException {
        if (getById(artist.getId()).isPresent()) {
            return false;
        }

        try(
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO ARTISTS VALUES (?,?)")) {
            statement.setLong(1, artist.getId());
            statement.setString(2, artist.getName());
            statement.execute();
            return true;
            } catch (SQLException ex) {
            throw new RecordStoreException(ex.getMessage(), ex);
        }

    }
    /**
     * {@inheritDoc}
     */

    @Override
    public boolean update(Artist artist)  {
        try(
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE ARTISTS SET NAME = ? WHERE ID = ?")) {
                statement.setString(1, artist.getName());
                statement.setLong(2, artist.getId());
                return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RecordStoreException(ex.getMessage(), ex);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Artist artist) {
        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM ARTISTS WHERE ID = ?")) {
            statement.setLong(1, artist.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RecordStoreException(ex.getMessage(), ex);
        }
    }
}