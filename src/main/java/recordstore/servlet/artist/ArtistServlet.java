package recordstore.servlet.artist;

import recordstore.dao.artist.ArtistDao;
import recordstore.dao.artist.DbArtistDao;
import recordstore.db.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public abstract class ArtistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final DataSource dataSource = SessionFactory.createDataSource();
    final ArtistDao dbArtistDao = new DbArtistDao(dataSource);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}

