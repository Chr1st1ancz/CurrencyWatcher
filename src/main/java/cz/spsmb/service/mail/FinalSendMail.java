package cz.spsmb.service.mail;

import cz.spsmb.tests.MailTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FinalSendMail {

    private static Logger logger = LogManager.getLogger(FinalSendMail.class);
    public static void send(String mailTo, String mailSubject, String mailBody) {

        if (!mailTo.equals("")) {
            try(InputStream input = MailTest.class.getClassLoader().getResourceAsStream("config.properties")){

                if (input == null) {
                    logger.error("Tried to load config.properties");
                    return;
                }

                Properties prop = new Properties();
                prop.load(input);
                Mail mailSend = new Mail();
                mailSend.setTo(mailTo);
                mailSend.setSubject(mailSubject);
                mailSend.setBody(mailBody);
                MailService mailService = new SimpleMailService(prop);
                mailService.send(mailSend);
            } catch (IOException ex) {
                logger.error("Unexpected error: " + ex);
            }
        } else
            logger.info("Empty Mail recipient");
    }

}
