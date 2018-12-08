package recordstore.servlet.album;

import com.zaxxer.hikari.HikariDataSource;
import recordstore.dao.album.AlbumDao;
import recordstore.dao.album.DbAlbumDao;
import recordstore.db.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public abstract class AlbumServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final DataSource dataSource = createDataSource();
  final AlbumDao dbAlbumDao = new DbAlbumDao(dataSource);

  private DataSource createDataSource() {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(SessionFactory.JDBC_URL_PATTERN);
    hikariDataSource.setPassword(SessionFactory.DB_PASS);
    return hikariDataSource;
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {}
}
