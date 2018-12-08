package recordstore.servlet.artist;

import com.zaxxer.hikari.HikariDataSource;
import recordstore.dao.artist.ArtistDao;
import recordstore.dao.artist.DbArtistDao;
import recordstore.db.SessionFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Artisti servlettien yläluokka, jossa artistinäkymille tarvittavat yhteiset metodit ja muuttujat
 */
public abstract class ArtistServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final DataSource dataSource = createDataSource();
  final ArtistDao dbArtistDao = new DbArtistDao(dataSource);

  private DataSource createDataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(SessionFactory.JDBC_URL_PATTERN);
    hikariDataSource.setPassword(SessionFactory.DB_PASS);
    return hikariDataSource;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {}

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {}
}
