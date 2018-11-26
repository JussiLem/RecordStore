package recordstore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.exception.RecordStoreException;

import static recordstore.db.SessionFactory.execute;

/**
 * This class can be used to start the H2 TCP server (or other H2 servers, for example the PG
 * server) inside a web application container such as Tomcat or Jetty. It can also open a database
 * connection.
 */
@WebListener
public class DbStarter implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(DbStarter.class);


  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    try{
      LOGGER.info("Luodaan tietokanta ja yhteys");
      execute();
    } catch (RecordStoreException e) {
      LOGGER.error("Tietokannan luonnisa virhe!", e);
    }


  }






    }
