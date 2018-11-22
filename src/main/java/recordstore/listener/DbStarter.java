package recordstore.listener;

import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.h2.Driver;
import org.h2.tools.Server;
import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.schema.ArtistSchemaSql;
import recordstore.schema.UserSchemaSql;

import static recordstore.schema.UserSchemaSql.CREATE_SCHEMA;

/**
 * This class can be used to start the H2 TCP server (or other H2 servers, for
 * example the PG server) inside a web application container such as Tomcat or
 * Jetty. It can also open a database connection.
 */
@WebListener
public class DbStarter implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbStarter.class);
    private Connection conn;
    private Server server;
    private static final String URL_PATTERN = "jdbc:h2:~/records";
    private static final String CREATE_DATABASE = "CREATE DATABASE records COLLATE Finnish_Swedish_CI_AS";
    private static final String USE_DATABASE = "USE records";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Driver.load();

            // This will get the setting from a context-param in web.xml if
            // defined:
            ServletContext servletContext = servletContextEvent.getServletContext();
            String url = getParameter(servletContext, "db.url", URL_PATTERN);
            String user = getParameter(servletContext, "db.user", "sa");
            String password = getParameter(servletContext, "db.password", "sa");

            // Start the server if configured to do so
            LOGGER.info("Creating database");
            String serverParams = getParameter(servletContext, "db.tcpServer", null);
            if (serverParams != null) {
                String[] params = StringUtils.arraySplit(serverParams, ' ', true);
                server = Server.createTcpServer(params);
                server.start();
            }


            // To access the database in server mode, use the database URL:
            // jdbc:h2:tcp://localhost/~/records

            conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            st.execute("CREATE SCHEMA IF NOT EXISTS TEST;"
                    + "SET SCHEMA TEST");
            st.execute("create table artists(id number, name varchar(100))");
            st.execute("insert into artists values (1, 'Thomas')");
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("select name from artists");
            while (rset.next()) {
                String name = rset.getString(1);
                LOGGER.info("Test added {} to artist table", name);
            }
         //   final ArtistDao dbDao = new DbArtistDao(conn);

            LOGGER.info("Current Schema: {}", conn.getSchema());

            LOGGER.info("Auto Commit Mode: {}", conn.getAutoCommit());

            servletContext.setAttribute("connection", conn);
            LOGGER.info("Database is running on {}", URL_PATTERN);

        } catch (SQLException e) {
            LOGGER.error("SQL ERROR: ", e);
        }
    }
    private static String getParameter(ServletContext servletContext,
                                       String key, String defaultValue) {
        String value = servletContext.getInitParameter(key);
        return value == null ? defaultValue : value;
    }

    /**
     * Get the connection.
     *
     * @return the connection
     */

    private Connection getConnection() {

        return conn;
    }

    public void execute() {
        LOGGER.info("Starting database migration");
        try{
            createDatabase();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    private void createDatabase() throws SQLException {
        String url = URL_PATTERN;
        try (Connection con = DriverManager.getConnection(url, "sa", "sa")) {
                executeStatement(con, CREATE_DATABASE);
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
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            Statement stat = conn.createStatement(); //NOSONAR
            stat.execute("SHUTDOWN");
            stat.close();
        } catch (SQLException e) {
            LOGGER.error("SQL virhe: ", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            LOGGER.error("SQL virhe: ", e);
        }
        if (server != null) {
            server.stop();
            server = null;
        }
    }

}
