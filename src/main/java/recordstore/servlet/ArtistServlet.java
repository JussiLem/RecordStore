package recordstore.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.ArtistDao;
import recordstore.dao.DbArtistDao;
import recordstore.data.Artist;
import recordstore.exception.RecordStoreException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import static recordstore.db.SessionFactory.DB_PASS;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServlet.class);
  private static final long serialVersionUID = 1L;
  private static final String JDBC_URL_PATTERN = "jdbc:mariadb://recordstoredb:3306/records";
  private final DataSource dataSource = createDataSource();
  private final ArtistDao dbArtistDao = new DbArtistDao(dataSource);


  private DataSource createDataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(JDBC_URL_PATTERN);
    hikariDataSource.setPassword(DB_PASS);
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
      LOGGER.info("Artists: {}", artistStream);
      request.setAttribute("artistStream", artistStream);

    } catch (SQLException e) {
      e.printStackTrace();
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
    String artistName = request.getParameter("name");
    Artist artist = new Artist(artistName);
    try {
      dbArtistDao.add(artist);
    } catch (SQLException e) {
      throw new RecordStoreException("Artistin lisäys epäonnistui", e);
    }
    LOGGER.info("Lisätty artisti: {}", artist);
  }
}
