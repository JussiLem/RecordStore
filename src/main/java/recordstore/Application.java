package recordstore;


import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.ArtistDao;
import recordstore.dao.DbArtistDao;
import recordstore.dao.InMemoryArtistDao;
import recordstore.data.Artist;
import recordstore.schema.ArtistSchemaSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final String URL = "jdbc:h2:~/dao";

    /**
     * Program entry point.
     *
     * @param args command line args.
     * @throws Exception jos virheitä.
     */
    public static void main(final String[] args) throws Exception {

        final ArtistDao inMemoryDao = new InMemoryArtistDao();
        performOperationsUsing(inMemoryDao);

        final DataSource dataSource = createDataSource();
        createSchema(dataSource);
        final ArtistDao dbDao = new DbArtistDao(dataSource);
        performOperationsUsing(dbDao);
        deleteSchema(dataSource);

  }

    private static void deleteSchema(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(ArtistSchemaSql.DELETE_SCHEMA_SQL);
        }
    }

    private static void createSchema(DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(ArtistSchemaSql.CREATE_SCHEMA_SQL);
        }
    }


    private static DataSource createDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(URL);
        return dataSource;
    }

  private static void performOperationsUsing(final ArtistDao artistDao) throws SQLException {
        addArtists(artistDao);
      LOGGER.info("artistDao.getAllArtists(): ");
      try (Stream<Artist> artistStream = artistDao.getAll()) {
          artistStream.forEach(artist -> LOGGER.info(String.valueOf(artist)));
      }
      LOGGER.info("artistDao.getArtistById(2): " + artistDao.getById(2));
      final Artist artist = new Artist(4, "Kid Rock");
      artistDao.add(artist);
      LOGGER.info("artistDao.getAllArtists(): " + artistDao.getAll());
      artist.setName("Robert James Ritchie");
      artistDao.update(artist);
      LOGGER.info("customerDao.getAllCustomers(): ");
      try (Stream<Artist> artistStream = artistDao.getAll()) {
          artistStream.forEach((art) -> LOGGER.info(String.valueOf(art)));
      }
      artistDao.delete(artist);
      LOGGER.info("customerDao.getAllCustomers(): " + artistDao.getAll());
  }

  private static void addArtists(ArtistDao artistDao) throws SQLException {
      for (Artist customer : generateSampleArtists()) {
          artistDao.add(customer);
      }
  }

    /**
     * Luo artisteja.
     *
     * @return lista artisteja.
     */
  private static List<Artist> generateSampleArtists() {
       final Artist artist1 = new Artist(1, "Bob Marley");
       final Artist artist2 = new Artist(2, "Elton John");
       final Artist artist3 = new Artist(3, "Marilyn Manson");
       final List<Artist> artists = new ArrayList<>();
       artists.add(artist1);
       artists.add(artist2);
       artists.add(artist3);

       return artists;
  }

}
