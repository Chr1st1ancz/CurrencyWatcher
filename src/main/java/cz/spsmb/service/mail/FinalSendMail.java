package cz.spsmb.service.mail;

import cz.spsmb.tests.MailTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FinalSendMail {

    public static void send(String mailTo, String mailSubject, String mailBody) {

        try(InputStream input = MailTest.class.getClassLoader().getResourceAsStream("config.properties")){

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
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
            ex.printStackTrace();
        }
    }

}
