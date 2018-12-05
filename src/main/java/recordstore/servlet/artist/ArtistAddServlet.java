package recordstore.servlet.artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.data.Artist;
import recordstore.exception.RecordStoreException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ArtistAddServlet", urlPatterns = "/addartists")
public class ArtistAddServlet extends ArtistServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistAddServlet.class);


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String artistName = request.getParameter("add-artist");
      Artist artist = new Artist(artistName);
      try {
        dbArtistDao.add(artist);
      } catch (SQLException e) {
        throw new RecordStoreException("Artistin lisäys epäonnistui", e);
      }
      LOGGER.info("Lisätty artisti: {}, albumi: {}", artist);
      request.getRequestDispatcher("/WEB-INF/views/artistAdd.jsp").include(request, response);

    } catch (IOException | ServletException e) {
      LOGGER.error("Servlet post virhe: {}", e);
    }
  }
}
