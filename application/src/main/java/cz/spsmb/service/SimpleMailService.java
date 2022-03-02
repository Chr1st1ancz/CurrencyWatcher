package cz.spsmb.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SimpleMailService implements MailService {

    public static final String MAIL_USER_NAME = "mail.user.name";
    public static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static Logger logger = LogManager.getLogger(SimpleMailService.class);

    private Session mailSession;
    private Properties properties;

    public SimpleMailService(Properties prop) {
        this.properties = prop;
        initMailSession(prop);
    }

    private void initMailSession(Properties prop) {
        String username = prop.getProperty(MAIL_USER_NAME);
        String password = prop.getProperty(MAIL_USER_PASSWORD);
        this.mailSession = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

       // mailSession.setDebug(true);
    }


    public void send(Mail mail) {
        logger.info("Odesílá se mail");
        try {
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(this.properties.getProperty(MAIL_USER_PASSWORD)));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            msg.setSentDate(new Date());
            msg.setSubject(mail.getSubject());
            msg.setText(mail.getBody());
            msg.getMessageNumber();

            Transport.send(msg);

        } catch(Exception E){
            logger.error("Odesílání mailu se pojebalo");
        }
        logger.debug("Success");
    }
}
