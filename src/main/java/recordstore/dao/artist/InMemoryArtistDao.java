package recordstore.dao.artist;

import recordstore.data.Artist;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class InMemoryArtistDao implements ArtistDao {

    private Map<Integer, Artist> idToArtist = new HashMap<>();

    @Override
    public Stream<Artist> getAll() {
        return idToArtist.values().stream();
    }

    @Override
    public Optional<Artist> getById(final int id)  {
        return Optional.ofNullable(idToArtist.get(id));
    }

    @Override
    public boolean add(final Artist artist)  {
    if (getById(artist.getId()).isPresent()) {
      return false;
        }
        idToArtist.put(artist.getId(), artist);
    return true;
    }

    @Override
    public boolean update(Artist artist)  {
        return idToArtist.replace(artist.getId(), artist) != null;

    }

    @Override
    public boolean delete(Artist artist)  {
        return idToArtist.remove(artist.getId()) != null;
    }
}
