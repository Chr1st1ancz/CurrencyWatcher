package com.company;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


public class SendEmail{

    public static void main(String [] args)
    {

        try {

            String host = "smtp.gmail.com";

            Properties props = new Properties();
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.ssl.trust", host);
            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("currencywatcher2022@gmail.com", "KartingWithMazlik");
                }
            });

                mailSession.setDebug(true);

                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress("KartingWithMazlik"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vit.vo@email.cz"));
                msg.setSentDate(new Date());
                msg.setSubject("Negr");

                msg.setText("Jsi pořádnej negr jak Smileskej komín");
                msg.getMessageNumber();

                Transport.send(msg);

            }catch(Exception E){
                System.out.println("Seš negr");
                System.out.println(E);
            }

    }
}
