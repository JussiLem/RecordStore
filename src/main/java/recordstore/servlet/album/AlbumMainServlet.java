package recordstore.servlet.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/album", name = "AlbumMainServlet")
public class AlbumMainServlet extends AlbumServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumMainServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.getRequestDispatcher("/WEB-INF/views/album/album.jsp").include(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("album.jsp ei saatu haettua", e);
        }

    }
}
