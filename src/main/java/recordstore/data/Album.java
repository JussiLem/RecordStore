package recordstore.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "ALBUMS")
public class Album {

    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue
    private int id;
    private String name;

    /**
     * Luo instanssin albumista
     */

    public Album(String name) {
        this.name = name;
        boolean isFilled = false;
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
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
