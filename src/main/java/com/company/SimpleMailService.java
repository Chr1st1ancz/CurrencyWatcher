package com.company;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import com.config.App1;

public class SimpleMailService implements MailService {

    private Session mailSession;

    public SimpleMailService(Properties prop) {
        initMailSession(prop);
    }

    private void initMailSession(Properties prop) {
        String username = prop.getProperty("mail.user.name");
        String password = prop.getProperty("mail.user.password");
        this.mailSession = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

       // mailSession.setDebug(true);
    }

    @Override
    public void send(Mail mail) {
        try {
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("KartingWithMazlik"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
            msg.setSentDate(new Date());
            msg.setSubject(mail.getSubject());
            msg.setText(mail.getBody());
            msg.getMessageNumber();

            Transport.send(msg);

        }catch(Exception E){
            System.out.println("Nevalí to :(");
            System.out.println(E);
        }
    }
}
