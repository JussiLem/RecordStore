package recordstore.data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Album {

    private static final AtomicInteger count = new AtomicInteger(0); // creates new id
    private Artist artist;
    private int id;
    private String name;
    private List<Track> tracks;

    /**
     * Luo instanssin albumista
     */

    public Album(String name) {
        this.name = name;
        boolean isFilled = false;
        id = count.incrementAndGet();
    }

    public Album(String name, boolean isFilled) {
        this.name = name;
        if (!isFilled) {
            id = count.incrementAndGet();
        } else {
         id = count.decrementAndGet();
        }

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
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
