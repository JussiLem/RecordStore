package recordstore.servlet.artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.artist.ArtistDao;
import recordstore.dao.artist.DbArtistDao;
import recordstore.db.SessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

@WebServlet(name = "ArtistDeleteServlet", urlPatterns = "/artists")
public class ArtistDeleteServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistDeleteServlet.class);
    private static final long serialVersionUID = 1L;
    private final DataSource dataSource = SessionFactory.createDataSource();
    private final ArtistDao dbArtistDao = new DbArtistDao(dataSource);

}
