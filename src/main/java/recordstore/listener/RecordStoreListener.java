package recordstore.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recordstore.db.SessionFactory;

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
      SessionFactory.execute();
    } catch (RuntimeException e) {
      LOGGER.error("Tietokannan luonnisa virhe!", e);
    }
  }
}
