package recordstore.data;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {

  private static final long serialVersionUID = 1L;
  private transient Artist artist;
  private int id;
  private String name;
  private transient List<Track> tracks;

  /**
   * Luo instanssin albumista
   */
  public Album(int id, String name, Artist artist) {
    super();
    this.artist = artist;
    this.name = name;
    this.id = id;
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

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }

  @Override
  public String toString() {
    return "Album{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

}
