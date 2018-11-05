package recordstore.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import recordstore.data.Artist;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class InMemoryArtistDaoTest {

  private InMemoryArtistDao dao;
  private static final Artist ARTIST = new Artist(1, "David Bowie");

  @BeforeEach
  void setUp() {
    dao = new InMemoryArtistDao();
    assertTrue(dao.add(ARTIST));
  }

  @Nested
  class NonExistingArtist {

    @Test
    void addingShouldResultInSuccess()  {
      try (Stream<Artist> allArtists = dao.getAll()) {
        assumeTrue(allArtists.count() == 1);
      }

      final Artist nonExistingArtist = new Artist(2, "Lady Gaga");
      boolean result = dao.add(nonExistingArtist);
      assertTrue(result);

      assertArtistCountIs(2);
      assertEquals(nonExistingArtist, dao.getById(nonExistingArtist.getId()).get());
    }

    @Test
    void deletionShouldBeFailureAndNotAffectExistingArtists() {
      final Artist nonExistingArtist = new Artist(2, "Robert Redford");
      boolean result = dao.delete(nonExistingArtist);

      assertFalse(result);
      assertArtistCountIs(1);
    }

    @Test
    void updationShouldBeFailureAndNotAffectExistingArtists() {
      final int nonExistingId = getNonExistingArtistId();
      final String name = "Elvis Presley";
      final Artist customer = new Artist(nonExistingId, name);
      boolean result = dao.update(customer);

      assertFalse(result);
      assertFalse(dao.getById(nonExistingId).isPresent());
    }

    @Test
    void retrieveShouldReturnNoArtist() {
      assertFalse(dao.getById(getNonExistingArtistId()).isPresent());
    }
  }

  @Nested
  class ExistingArtist {

    @Test
    void addingShouldResultInFailureAndNotAffectExistingArtists() {
      boolean result = dao.add(ARTIST);

      assertFalse(result);
      assertArtistCountIs(1);
      assertEquals(ARTIST, dao.getById(ARTIST.getId()).get());
    }

    @Test
    void deletionShouldBeSuccessAndArtistShouldReturnUpdatedInformation() {
      final String newName = "Action Bronson";
      final Artist artist = new Artist(ARTIST.getId(), newName);
      boolean result = dao.update(artist);

      assertTrue(result);

      final Artist artist1 = dao.getById(ARTIST.getId()).get();
      assertEquals(newName, artist1.getName());
    }

    @Test
    void retrieveShouldReturnCustomer() {
      Optional<Artist> optionalArtist = dao.getById(ARTIST.getId());

      assertTrue(optionalArtist.isPresent());
      assertEquals(ARTIST, optionalArtist.get());
    }
  }

  private int getNonExistingArtistId() {
    return 999;
  }

  private void assertArtistCountIs(int count) {
    try (Stream<Artist> allArtists = dao.getAll()) {
      assertEquals(count, allArtists.count());
    }
  }
}