package recordstore.servlet.album;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Album;
import recordstore.data.Artist;
import recordstore.exception.RecordStoreException;

@WebServlet(urlPatterns = "/album/addalbum", name = "AlbumServlet")
public class AlbumAddServlet extends AlbumServlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlbumAddServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setCharacterEncoding("UTF-8");
      request.getRequestDispatcher("/WEB-INF/views/album/album.jsp").include(request, response);
    } catch (UnsupportedEncodingException | ServletException e) {
      LOGGER.error("Servlet virhe: {}", e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      req.setCharacterEncoding("UTF-8");
      String albumName = req.getParameter("album");
      int albumId = Integer.parseInt(req.getParameter("id"));
      String artistName = req.getParameter("artist");

      resp.setCharacterEncoding("UTF-8");

      Artist artist = new Artist(artistName);
      Album album = new Album(albumId, albumName, artist);
      try {
        dbAlbumDao.add(album);
        LOGGER.info("Lisätty albumi: {}", album);
      } catch (SQLException e) {
        LOGGER.error("Albumin lisäys epäonnistui: {}", e.getMessage());
      }

    } catch (NumberFormatException | UnsupportedEncodingException | RecordStoreException e) {
      LOGGER.error("Poikkeus albumin luonnissa, {}", e);
    }

  }
}
