package cz.spsmb;

import cz.spsmb.gui.GuiApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug("Application started");
        GuiApplication.main(args);
    }
}
