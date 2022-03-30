package cz.spsmb;

import cz.spsmb.gui.GuiApplication;
import cz.spsmb.tests.MailTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

public class Application {

    private static InputStream input = MailTest.class.getClassLoader().getResourceAsStream("config.properties");
    private static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args)  {
        logger.debug("application started");
        GuiApplication.main(args);
    }

}
