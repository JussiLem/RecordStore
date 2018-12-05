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

@WebServlet(name = "ArtistDeleteServlet", urlPatterns = "/deleteartist")
public class ArtistDeleteServlet extends ArtistServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            request.getRequestDispatcher("/WEB-INF/views/artistDelete.jsp").include(request, response);
        } catch (IOException e) {
            LOGGER.error("ei saatu ohjattua jsp sivulle");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            String artistName = request.getParameter("remove-artist");
            Artist artist = new Artist(artistName);
            if (artist.getName() != null) {
                try {
                    dbArtistDao.delete(artist);
                    LOGGER.info("Poistettu artisti: {}, albumi: {}", artist);
                } catch (SQLException e) {
                    throw new RecordStoreException("Artistin poistaminen epäonnistui", e);
                }
            } else {
                LOGGER.info("Poistettavaa artistia ei löytnyt: {}", artist);
            }


    }
}
