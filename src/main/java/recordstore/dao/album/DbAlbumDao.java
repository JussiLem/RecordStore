package recordstore.dao.album;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Album;
import recordstore.data.Artist;
import recordstore.exception.RecordStoreException;

import javax.sql.DataSource;
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

public class DbAlbumDao implements AlbumDao {
  private static final Logger LOGGER = LoggerFactory.getLogger(DbAlbumDao.class);
  private final DataSource dataSource;

  public DbAlbumDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Stream<Album> getAll() throws SQLException {
    Connection connection;
    try {
      connection = getConnection();
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM albums"); // NOSONAR
      ResultSet resultSet = statement.executeQuery(); // NOSONAR
      return StreamSupport.stream(
              new Spliterators.AbstractSpliterator<Album>(Long.MAX_VALUE, Spliterator.ORDERED) {

                @Override
                public boolean tryAdvance(Consumer<? super Album> action) {
                  try {
                    if (!resultSet.next()) {
                      return false;
                    }
                    action.accept(createAlbums(resultSet));
                    return true;
                  } catch (SQLException e) {
                    throw new RuntimeException(e); // NOSONAR
                  }
                }
              },
              false)
          .onClose(() -> mutedClose(connection, statement, resultSet));
    } catch (SQLException e) {
      throw new RecordStoreException(e.getMessage(), e);
    }
  }

  private Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  private void mutedClose(Connection connection, PreparedStatement statement, ResultSet resultSet) {
    try {
      resultSet.close();
      statement.close();
      connection.close();
    } catch (SQLException e) {
      LOGGER.info("SQL ERROR {}", e.getMessage());
    }
  }

  private Album createAlbums(ResultSet resultSet) throws SQLException {
    return new Album(
        resultSet.getInt("albumId"),
        resultSet.getString("name"),
        resultSet.getObject("artist", Artist.class));
  }

  @Override
  public Optional<Album> getById(int id) throws SQLException {
    ResultSet resultSet = null;

    try (Connection connection = getConnection();
        PreparedStatement statement =
            connection.prepareStatement("SELECT * FROM albums WHERE albumId = ?")) {

      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        return Optional.of(createAlbums(resultSet));
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

  @Override
  public boolean add(Album album) throws SQLException {
    int id = album.getId();
    if (getById(id).isPresent()) {
      return false;
    }

    return getAlbumStatement(album, id);
  }

  private boolean getAlbumStatement(Album album, int id) {
    try (Connection connection = getConnection();
        PreparedStatement statement =
            connection.prepareStatement("INSERT INTO albums VALUES (?,?,?)")) {
      statement.setLong(1, id);
      String name = album.getName();
      statement.setString(2, name);
      Artist albumArtist = album.getArtist();
      Gson gson = new Gson();
      JsonParser parser = new JsonParser();
      JsonObject albumArtistJson = (JsonObject) parser.parse(gson.toJson(albumArtist));
      int albumArtistId = albumArtistJson.get("id").getAsInt();
      String albumArtistName = albumArtistJson.get("name").getAsString();
      statement.setInt(3, albumArtistId);

      statement.execute();
      return true;
    } catch (SQLException ex) {
      throw new RecordStoreException(ex.getMessage(), ex);
    }
  }

  @Override
  public boolean update(Album album) throws SQLException {
    try (Connection connection = getConnection();
        PreparedStatement statement =
            connection.prepareStatement("UPDATE albums SET name = ? WHERE albumId = ?")) {
      statement.setString(1, album.getName());
      statement.setLong(2, album.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      throw new RecordStoreException(ex.getMessage(), ex);
    }
  }

  @Override
  public boolean delete(Album album) throws SQLException {
    try (Connection connection = getConnection();
        PreparedStatement statement =
            connection.prepareStatement("DELETE FROM albums WHERE albumId = ?")) {
      statement.setLong(1, album.getId());
      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      throw new RecordStoreException(ex.getMessage(), ex);
    }
  }
}
