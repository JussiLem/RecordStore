package recordstore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.exception.RecordStoreException;

import static recordstore.db.SessionFactory.execute;

/**
 * Kuuntelee sovelluksen käynnistymistä
 */
@WebListener
public class RecordStoreListener implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(RecordStoreListener.class);

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {

    try {
      LOGGER.info("Luodaan tietokanta ja yhteys");
      execute();
    } catch (RecordStoreException e) {
      LOGGER.error("Tietokannan luonnisa virhe!", e);
    }
  }
}
