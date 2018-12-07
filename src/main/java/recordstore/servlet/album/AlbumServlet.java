package recordstore.servlet.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.dao.album.AlbumDao;
import recordstore.dao.album.DbAlbumDao;
import recordstore.data.Album;
import recordstore.db.SessionFactory;
import recordstore.exception.RecordStoreException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/addalbum", name = "AlbumServlet")
public class AlbumServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumServlet.class);
    private static final long serialVersionUID = 1L;
    private final DataSource dataSource = SessionFactory.createDataSource();
    private final AlbumDao dbAlbumDao = new DbAlbumDao(dataSource);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/albumList.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String albumName = req.getParameter("album");
        Album album = new Album(albumName);
        try {
            dbAlbumDao.add(album);
        } catch (SQLException e) {
            throw new RecordStoreException("Albumin lisäys epäonnistui", e);
        }
        LOGGER.info("Lisätty albumi: {}", album);
    }
    }

