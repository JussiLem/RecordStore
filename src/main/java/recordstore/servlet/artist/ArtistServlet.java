package recordstore.servlet.artist;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.album.AlbumDao;
import recordstore.dao.album.DbAlbumDao;
import recordstore.dao.artist.ArtistDao;
import recordstore.dao.artist.DbArtistDao;
import recordstore.data.Artist;
import recordstore.db.SessionFactory;
import recordstore.exception.RecordStoreException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServlet.class);
  private static final long serialVersionUID = 1L;
  private final DataSource dataSource = createDataSource();
  private final ArtistDao dbArtistDao = new DbArtistDao(dataSource);
  private final AlbumDao dbAlbumDao = new DbAlbumDao(dataSource);

  private DataSource createDataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(SessionFactory.JDBC_URL_PATTERN);
    hikariDataSource.setPassword(SessionFactory.DB_PASS);
    return hikariDataSource;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    try {
      String button = request.getParameter("find");
      if (Objects.equals(button, "all")) {
        getArtists(request);
      }
      request.getRequestDispatcher("/WEB-INF/artistList.jsp").include(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.error("Servlet get virhe: {}", e);
    }
  }

  private void getArtists(HttpServletRequest request) {
    try {
    Object[] artistStream = dbArtistDao.getAll().toArray();
    if (artistStream.length > 0) {
        request.setAttribute("artistStream", artistStream);
    } else {
        request.setAttribute("message", "Artist list is empty");
    }

    } catch (SQLException e) {
      throw new RecordStoreException("Artistia ei saatu haettua", e);
    }
  }

    @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
      try {

        generateArtists(request);
        request.getRequestDispatcher("/WEB-INF/artistList.jsp").include(request, response);

      } catch (IOException | ServletException e) {
        LOGGER.error("Servlet post virhe: {}", e);
      }
    }

  private void generateArtists(HttpServletRequest request) {
    String artistName = request.getParameter("artist");
    Artist artist = new Artist(artistName);
    try {
      dbArtistDao.add(artist);
    } catch (SQLException e) {
      throw new RecordStoreException("Artistin lisäys epäonnistui", e);
    }
    LOGGER.info("Lisätty artisti: {}, albumi: {}", artist);
  }
}
