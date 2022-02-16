package com.config;

import com.company.Mail;
import com.company.MailService;
import com.company.SimpleMailService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App1 {

    public static void main(String[] args) {

        try(InputStream input = App1.class.getClassLoader().getResourceAsStream("config.properties")){

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            Properties prop = new Properties();
            prop.load(input);
            Mail mailSend = new Mail();
            mailSend.setTo("christian.abraham@email.cz");
            mailSend.setSubject("Mailsender Test");
            mailSend.setBody("Success");
            MailService mailService = new SimpleMailService(prop);
            mailService.send(mailSend);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
