package recordstore.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.servlet.artist.ArtistServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecordStoreServlet", urlPatterns = "/home")
public class RecordStoreServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      try{
          request.getRequestDispatcher("/WEB-INF/views/index.jsp").include(request, response);
      } catch (ServletException | IOException e) {
          LOGGER.error("index.jsp ei saatu haettua", e);
      }

  }
}
