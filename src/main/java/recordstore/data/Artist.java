package recordstore.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Artisti POJO edustaa tietoja, jotka luetaan kannasta.
 *
 */

@Entity
@Table(name = "ARTISTS")
public class Artist {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    /**
     * Luo instanssin artistista
     */
    public Artist(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        boolean isEqual = false;
        if (this == o) {
            isEqual = true;
        } else if (o instanceof Artist) {
            final Artist artist = (Artist) o;
            if (getId() == artist.getId()) {
                isEqual = true;
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
