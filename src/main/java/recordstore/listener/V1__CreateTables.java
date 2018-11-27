package recordstore.listener;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.PreparedStatement;

public class V1__CreateTables extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {

    try (PreparedStatement statement =
        context
            .getConnection()
            .prepareStatement("CREATE TABLE ARTISTS (ID NUMBER, NAME VARCHAR(100))")) {
      statement.execute();
    }
  }
}
