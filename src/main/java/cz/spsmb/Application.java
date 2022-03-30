package cz.spsmb;

import cz.spsmb.service.data.CSOBDataConvertor;
import cz.spsmb.service.data.SimpleDataFetcher;
import cz.spsmb.service.mail.SimpleMailService;
import cz.spsmb.tests.MailTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class Application {

    private static InputStream input = MailTest.class.getClassLoader().getResourceAsStream("config.properties");
    private static Logger logger = LogManager.getLogger(SimpleMailService.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties prop = new Properties();
        prop.load(input);

        logger.debug("Looper Start");
        do {
            logger.debug("Looper - new Loop (" + new Date() + ")");

            /* Main Loop */
            // MailTest.Start();
            new CSOBDataConvertor().convert(new SimpleDataFetcher().getContent("https://www.csob.cz/portal/lide/kurzovni-listek-old/-/date/kurzy.txt"));

            /* Looper Timer */
            Thread.sleep((long) (60000 * Double.parseDouble(prop.getProperty("app.timer.minutes"))));
        } while (true);
        // logger.debug("Looper Ended");
    }

}
