package com.company;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;


public class SendEmail{

    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadej mailus");
        String mail = sc.nextLine();

        Mail mailSend = new Mail();
        mailSend.setTo(mail);
        mailSend.setSubject("Mailsender Test");
        mailSend.setBody("Success");

        MailService mailService = new SimpleMailService();
        mailService.send(mailSend);

    }
}
