package recordstore.db;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFactory {

  private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactory.class);
  private static final Config config = ConfigFactory.load().getConfig("database");
  public static final String JDBC_URL_PATTERN = config.getString("host");
  public static final String DB_PASS = config.getString("password");
  private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
  private static final String DB_USER = config.getString("user");

  private SessionFactory() {
    // vain staattisia metodeja
  }

  private static DataSource createDataSource() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(JDBC_URL_PATTERN);
    config.setDriverClassName(JDBC_DRIVER);
    config.setUsername(DB_USER);
    config.setPassword(DB_PASS);
    config.setAutoCommit(false);
    config.setConnectionTestQuery("select 1");
    return new HikariDataSource(config);
  }

  private static void flywayMigration(DataSource dataSource) {
    try {
      LOGGER.info("Migraatio alkaa");
      Flyway flyway = Flyway.configure().locations("db.migration").dataSource(dataSource).load();
      LOGGER.info("Käytössä {} tietokannan hallintaan", "sql");
      flyway.migrate();
    } catch (FlywayException e) {
      LOGGER.error("Flyway virhe: {}", e);
      throw e;
    }
  }

  public static void execute() {
    try {
      DataSource dataSource = createDataSource();
      flywayMigration(dataSource);
    } catch (RuntimeException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
