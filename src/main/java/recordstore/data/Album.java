package recordstore.data;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "ALBUMS")
public class Album {

    private static final AtomicInteger count = new AtomicInteger(0); // creates new id
    private int id; // albumId
    private String name;

    /**
     * Luo instanssin albumista
     */

    public Album(String name) {
        this.name = name;
        boolean isFilled = false;
        id = count.incrementAndGet();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumId")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
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
