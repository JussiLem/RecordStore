package recordstore.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.ArtistDao;
import recordstore.dao.InMemoryArtistDao;
import recordstore.data.Artist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ArtistServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {
    private final ArtistDao inMemoryDao = new InMemoryArtistDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistServlet.class);
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try{
            String button = request.getParameter("find");
            if (Objects.equals(button, "")) {
                findArtists(request);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/artistList.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error("Servlet get virhe: {}", e);
        }

    }

    private void findArtists(HttpServletRequest request) {
        try {
            Stream<Artist> results = inMemoryDao.getAll();
            request.setAttribute("rows", results);

        } catch (SQLException e) {
            LOGGER.error("Artisteja ei saatu haettua {}", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            generateArtists(request);
            request.getRequestDispatcher("/WEB-INF/artistList.jsp").include(request, response);
        } catch (SQLException | IOException | ServletException e) {
            LOGGER.error("Servlet post virhe: {}", e);
        }
    }


    private void generateArtists(HttpServletRequest request) throws SQLException {
        String artistName = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("number"));
        if (id == 0 || id < 0) {
            LOGGER.error("id viallinen: {}", id);
            throw new NumberFormatException("Id pitää olla positiivinen ja suurempi kuin 0");
        }
        Artist artist = new Artist(id, artistName);
        inMemoryDao.add(artist);
        LOGGER.info("Lisätty artisti: {}", artist.toString());
    }
}
