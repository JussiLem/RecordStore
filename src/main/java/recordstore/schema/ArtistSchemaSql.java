package recordstore.schema;

/**
 * Artist Schema SQL Class
 */
public class ArtistSchemaSql {

  private ArtistSchemaSql() {}

  public static final String CREATE_SCHEMA_SQL =
      "CREATE TABLE ARTISTS (ID NUMBER, NAME VARCHAR(100))";

  public static final String DELETE_SCHEMA_SQL = "DROP TABLE ARTISTS";
}
