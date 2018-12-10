package recordstore.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Track {

  private static final AtomicInteger count = new AtomicInteger(0);
  private int id;
  private Artist artist;
  private String name;
  private List<Album> albums;

  public Track(String name) {
    this.name = name;
    id = count.incrementAndGet();
  }

  public Track(Artist artist, String name, List<Album> albums) {
    super();
    this.id = count.incrementAndGet();
    this.artist = artist;
    this.name = name;
    if (albums != null) {
      this.albums = albums;
    } else {
      this.albums = new ArrayList<>();
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

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  public void setAlbums(List<Album> albums) {
    this.albums = albums;
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
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Track track = (Track) o;
    return getId() == track.getId() &&
        Objects.equals(getName(), track.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}
