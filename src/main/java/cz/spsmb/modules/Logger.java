package cz.spsmb.modules;

import org.apache.logging.log4j.LogManager;

public class Logger {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }

}
