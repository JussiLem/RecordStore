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

@WebServlet(name = "ArtistDeleteServlet", urlPatterns = "/artist/deleteartist")
public class ArtistDeleteServlet extends ArtistServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistDeleteServlet.class);

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    String artistName = request.getParameter("remove-artist");
    Artist artist = new Artist(artistName, true);
    if (artist.getName() != null) {
      try {
        dbArtistDao.delete(artist);
        LOGGER.info("Poistettu artisti: {}", artist);
        request.getRequestDispatcher("/WEB-INF/views/artist/artist.jsp").include(request, response);
      } catch (SQLException | ServletException | IOException e) {
        throw new RecordStoreException("Artistin poistaminen epäonnistui", e);
      }
    } else {
      LOGGER.info("Poistettavaa artistia ei löytnyt: {}", artist);
    }
  }
}
