package recordstore.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DisplayName("Writing assertions for objects")
class ArtistTest {
    private static Artist artist;
    private static final int ID = 1;
    private static final String NAME = "Bon Jovi";

    @BeforeAll
    static void setUp() {
        artist = new Artist(ID, NAME);
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
        final Artist otherArtist = new Artist(newId, NAME);
        assertNotEquals(artist, otherArtist);
        assertNotEquals(artist.hashCode(), otherArtist.hashCode());
    }
    @Test
    void equalsWithSameObjectValues() {
        final Artist otherArtist = new Artist(ID, NAME);
        assertEquals(artist, otherArtist);
        assertEquals(artist.hashCode(), otherArtist.hashCode());
    }

    @Test
    void equalsWithSameObjects() {
        assertEquals(artist, artist);
        assertEquals(artist.hashCode(), artist.hashCode());
    }
    @Test
    void testToString() {
        assertEquals(String.format("Artist{id=%s, name='%s'}",
                artist.getId(), artist.getName()), artist.toString());
    }


}
