package com.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App1 {

    public static void main(String[] args) {

        try(InputStream input = App1.class.getClassLoader().getResourceAsStream("config.properties")){

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            prop.load(input);

            System.out.println(prop.getProperty("db.url1"));
            System.out.println(prop.getProperty("db.user"));
            System.out.println(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
