package recordstore.dao;

import recordstore.data.Artist;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public interface ArtistDao {

    Stream<Artist> getAll() throws SQLException;

    Optional<Artist> getById(int id) throws SQLException;

    boolean add(Artist artist) throws SQLException;

    boolean update(Artist artist) throws SQLException;

    boolean delete(Artist artist) throws SQLException;
}
