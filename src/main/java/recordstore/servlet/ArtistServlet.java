package recordstore.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.ArtistDao;
import recordstore.dao.InMemoryArtistDao;
import recordstore.data.Artist;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {
    private final ArtistDao inMemoryDao = new InMemoryArtistDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/artistList.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


        try {
            generateArtists(request);
            request.getRequestDispatcher("/WEB-INF/artistList.jsp").include(request, response);
        } catch (SQLException | IOException | ServletException e) {
            LOGGER.error("SQL ERROR: {}", e);
        }
    }


    private void generateArtists(HttpServletRequest request) throws SQLException {
        String artistName = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("number"));
        if (id == 0 || id < 0) {
            LOGGER.error("id viallinen: {}", id);
            throw new NumberFormatException("Id pitää olla positiivinen ja suurempi kuin 0");
        }
        Artist artistList = new Artist(id, artistName);
        inMemoryDao.add(artistList);
        LOGGER.info("Lisätty artisti: {}", artistList.toString());
    }
}
