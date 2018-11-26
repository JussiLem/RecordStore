package recordstore.db;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class SessionFactory {

    private SessionFactory() {
        // vain staattisia metodeja
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactory.class);
    private static final Config config = ConfigFactory.load().getConfig("database");
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL_PATTERN = "jdbc:mariadb://recordstoredb:3306/records";
    private static final String host = config.getString("host");
    private static final String DbUser = config.getString("user");
    private static final String DbPass = config.getString("password");


    private static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL_PATTERN);
        config.setDriverClassName(JDBC_DRIVER);
        config.setUsername(DbUser);
        config.setPassword(DbPass);
        config.setAutoCommit(false);
        return new HikariDataSource(config);
    }

    private static void flywayMigration(DataSource dataSource) {

        try{
            LOGGER.info("Migraatio alkaa");
            Flyway flyway = Flyway.configure()
                    .locations("db.migration")
                    .dataSource(dataSource)
                    .load();
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
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
/*
    private void createDatabase() throws SQLException {
        String url = JDBC_URL_PATTERN;
        try (Connection con = DriverManager.getConnection(url, "sa", "sa")) {
            executeStatement(con, USE_DATABASE);
        } finally {
            LOGGER.info("Creating database is done for url {}", url);
        }
    }

    private void executeStatement(Connection con, String sql) throws SQLException {
        LOGGER.info("Executing statement: {}", sql);
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }
    */
}
