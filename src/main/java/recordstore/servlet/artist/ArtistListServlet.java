package recordstore.servlet.artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.exception.RecordStoreException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ArtistListServlet", urlPatterns = "/listartists")
public class ArtistListServlet extends ArtistServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(ArtistListServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    try {
      String button = request.getParameter("find");
      if (Objects.equals(button, "all")) {
        getArtists(request);
      }
      request.getRequestDispatcher("/WEB-INF/views/artistList.jsp").include(request, response);
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
}
