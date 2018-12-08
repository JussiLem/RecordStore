package recordstore.servlet.artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/artist", name = "ArtistMainServlet")
public class ArtistMainServlet extends ArtistServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistMainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/WEB-INF/views/artist/artist.jsp").include(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("index.jsp ei saatu haettua", e);
        }

    }
}
