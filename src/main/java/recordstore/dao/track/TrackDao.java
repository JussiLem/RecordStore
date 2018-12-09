package recordstore.dao.track;

import recordstore.data.Track;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public interface TrackDao {

    Stream<Track> getAll() throws SQLException;

    Optional<Track> getById(int id) throws SQLException;

    boolean add(Track track) throws SQLException;

    boolean update(Track track) throws SQLException;

    boolean delete(Track track) throws SQLException;
}
