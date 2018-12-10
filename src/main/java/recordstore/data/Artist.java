package recordstore.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Artisti POJO edustaa tietoja, jotka luetaan kannasta.
 */

public class Artist implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final AtomicInteger count = new AtomicInteger(0);
  private int id;
  private String name;

  /**
   * Luo instanssin artistista
   */
  public Artist(final String name, int id) {
    this.name = name;
    this.id = id;
  }

  public Artist(final String name) {
    this.name = name;
    id = count.incrementAndGet();
  }

  public Artist(String name, boolean isFilled) {
    this.name = name;
    if (isFilled) {
      id = count.decrementAndGet();
    } else {
      id = count.incrementAndGet();
    }
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
    return "Artist{" + "id=" + getId() + ", name='" + getName() + '\'' + '}';
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
