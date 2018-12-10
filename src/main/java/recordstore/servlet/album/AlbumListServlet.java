package recordstore.servlet.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Album;
import recordstore.exception.RecordStoreException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/album/listalbum", name = "AlbumListServlet")
public class AlbumListServlet extends AlbumServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(AlbumListServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String button = request.getParameter("find");
      if (Objects.equals(button, "all")) {
        getAlbums(request);
      }
      request.getRequestDispatcher("/WEB-INF/views/album/albumList.jsp").include(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.error("Servlet get virhe: {}", e);
    }
  }

  private void getAlbums(HttpServletRequest request) {
      try (Stream<Album> albumStream = dbAlbumDao.getAll()) {
          albumStream.forEach(album -> LOGGER.info(String.valueOf(album)));
      } catch (SQLException e) {
          LOGGER.error("SQL virhe {}", e);
      }

  }
}
