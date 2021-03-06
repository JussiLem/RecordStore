package recordstore.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** Testaa {@link Artist}. */
@DisplayName("Writing assertions for Artist")
public class ArtistTest {
  private static Artist artist;
  private static final int ID = 1;
  private static final String NAME = "Bon Jovi";

  @BeforeAll
  static void setUp() {
    artist = new Artist(NAME, ID);
  }

  @Test
  @DisplayName("Should be equal")
  void getAndSetId() {
    final int newId = 2;
    artist.setId(newId);
    assertEquals(newId, artist.getId());
  }

  @Test
  @DisplayName("Shouldn be able to set name")
  void getAndSetName() {
    final String newName = "Madonna";
    artist.setName(newName);
    assertEquals(newName, artist.getName());
  }

  @Test
  @DisplayName("Shouldn't be equal")
  void notEqualWithDifferentId() {
    final int newId = 2;
    final Artist otherArtist = new Artist(NAME, newId);
    assertNotEquals(artist, otherArtist);
    assertNotEquals(artist.hashCode(), otherArtist.hashCode());
  }

  @Test
  @DisplayName("Equals with the same object values")
  void equalsWithSameObjectValues() {
    final Artist otherArtist = new Artist(NAME, ID);
    assertEquals(artist, otherArtist);
    assertEquals(artist.hashCode(), otherArtist.hashCode());
  }

  @Test
  @DisplayName("Equals with the same objects")
  void equalsWithSameObjects() {
    assertEquals(artist, artist);
    assertEquals(artist.hashCode(), artist.hashCode());
  }

  @Test
  @DisplayName("Test to string")
  void testToString() {
    assertEquals(
        String.format("Artist{id=%s, name='%s'}", artist.getId(), artist.getName()),
        artist.toString());
  }
}
