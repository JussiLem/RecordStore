package recordstore.schema;

/**
 * Artist Schema SQL Class
 */
public class ArtistSchemaSql  {

  private ArtistSchemaSql() {}

  public static final String CREATE_TABLE_ARTISTS =
      "CREATE TABLE `ARTISTS`\n"
          + "(\n"
          + "  `id` INT AUTO_INCREMENT primary key NOT NULL,\n"
          + "  `name`     VARCHAR(100)                   NOT NULL UNIQUE COMMENT 'artistin nimi'\n"
          + ");";
  public static final String DROP_TABLE_ARTISTS = "DROP TABLE ARTISTS";

}
