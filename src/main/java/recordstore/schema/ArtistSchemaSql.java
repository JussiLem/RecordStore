package recordstore.schema;

/**
 * Artist Schema SQL Class
 */
public class ArtistSchemaSql  {

  private ArtistSchemaSql() {}
  public static final String CREATE_TABLE_ARTISTS =
      "CREATE TABLE ARTISTS (ID NUMBER, NAME VARCHAR(100))";
  public static final String DROP_TABLE_ARTISTS = "DROP TABLE ARTISTS";

}
