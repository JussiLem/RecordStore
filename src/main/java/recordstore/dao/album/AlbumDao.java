package recordstore.dao.album;

import recordstore.data.Album;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public interface AlbumDao {

    Stream<Album> getAll() throws SQLException;

    Optional<Album> getById(int id) throws SQLException;

    boolean add(Album album) throws SQLException;

    boolean update(Album album) throws SQLException;

    boolean delete(Album album) throws SQLException;
}
