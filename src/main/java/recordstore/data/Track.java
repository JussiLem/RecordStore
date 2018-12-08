package recordstore.data;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Track {

    private static final AtomicInteger count = new AtomicInteger(0);

    private int id;
    private String name;

    public Track(String name) {
        this.name = name;
        id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return getId() == track.getId() &&
                Objects.equals(getName(), track.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
