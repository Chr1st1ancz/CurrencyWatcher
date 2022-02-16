package com.company;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public interface MailService {

    void send(Mail mail);
}
