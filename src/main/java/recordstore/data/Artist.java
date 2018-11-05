package recordstore.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Artist {
    private static final Logger LOGGER = LoggerFactory.getLogger(Artist.class);
    private int id;
    private String name;

    public Artist(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
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
        } else if (o != null && getClass() == o.getClass()) {
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
