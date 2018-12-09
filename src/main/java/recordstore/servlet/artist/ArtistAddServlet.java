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

@WebServlet(name = "ArtistAddServlet", urlPatterns = "/artist/addartists")
public class ArtistAddServlet extends ArtistServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistAddServlet.class);

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String artistName = request.getParameter("add-artist");
      try {
        createArtist(artistName);
        request.getRequestDispatcher("/WEB-INF/views/artist/artist.jsp").forward(request, response);
      } catch (RecordStoreException e) {
        request.setAttribute("message", "Artisti on jo olemassa");
      }

    } catch (IOException | ServletException e) {
      LOGGER.error("Servlet post virhe: {}", e);
    }
  }

  private void createArtist(String artistName) {
    try {
      Artist newArtist = new Artist(artistName, false);
      dbArtistDao.add(newArtist);
      LOGGER.info("Lisätty artisti: {}", newArtist);
    } catch (SQLException e) {
      throw new RecordStoreException("Artistin lisäys epäonnistui", e);
    }
  }
}
