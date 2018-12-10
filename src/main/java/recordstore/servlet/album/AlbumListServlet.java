package recordstore.servlet.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.exception.RecordStoreException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

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
      request.getRequestDispatcher("/WEB-INF/views/album/album.jsp").forward(request, response);
    } catch (ServletException | IOException e) {
      LOGGER.error("Servlet get virhe: {}", e);
    }
  }

  private void getAlbums(HttpServletRequest request) {
    try {
      Object[] albumStream = dbAlbumDao.getAll().toArray();
      if (albumStream.length > 0) {
        request.setAttribute("albumStream", albumStream);
        LOGGER.debug("Haettu: {}", albumStream);
      } else {
        request.setAttribute("message", "Albums table is empty");
      }

    } catch (SQLException e) {
      throw new RecordStoreException("Albumeita ei saatu haettua", e);
    }
  }
}
