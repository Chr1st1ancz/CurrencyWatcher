package com.company;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


public class SendEmail extends Object{

    public static void main(String [] args)
    {

        try{

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.mail.yahoo.com");
            props.put("mail.smtp.host", "smtp.mail.seznam.cz");
            props.put("mail.smtp.host", "smtp.mail.email.cz");
            props.put("mail.smtp.host", "smtp.mail.gmail.com");// for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("CurrencyWatcher@email.cz", "KartingWithMazlik");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage( mailSession );

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom( new InternetAddress( "KartingWithMazlik" ) );
            msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse("filipusnovakus@seznam.cz") );
            msg.setSentDate( new Date());
            msg.setSubject( "Hello World!" );

            //--[ Create the body of the mail
            msg.setText( "Hello from my first e-mail sent with JavaMail" );

            //--[ Ask the Transport class to send our mail message
            Transport.send( msg );

        }catch(Exception E){
            System.out.println( "Oops something has gone pearshaped!");
            System.out.println( E );
        }
    }
}
