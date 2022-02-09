package com.example.currencywatcher.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.currencywatcher.HelloApplication;
import org.h2.tools.*;

public class Database {
    private static String currencyString;

    public static void startDBServer() throws SQLException {
        Server server = Server.createTcpServer().start();
        System.out.println("Databáze je " + server.getStatus() + ".");
        SimpleResultSet rs = new SimpleResultSet();
        Map currencyList = new HashMap();
        currencyList.put("CZK", true);
        currencyList.put("EURO", false);
        currencyList.put("USD", true);

        rs.addColumn("EMAIL", Types.VARCHAR, 255, 0);
        rs.addColumn("FOLLOWEDCURRENCIES", Types.JAVA_OBJECT, 255, 0);
        rs.addRow("bob.meier@abcde.abc", currencyList);
        rs.addRow("john.jones@abcde.abc", currencyList);
        new Csv().write("data/users.csv", rs, null);

        createUser("bob.meier@abcde.abcc");
        createUser("john.jones@abcde.abc");
        createUser("kokot@negr.lol");

    }

    public static void createUser(String inputEmail) throws SQLException {
        //získat data z javafx app, nebude se nic hashovat protože to je moc práce, kdo storuje raw hesla je boss 😎😎😎😎😎
        Map currencyList = new HashMap();
        ResultSet rs = new Csv().read("data/users.csv", null, null);
        while (rs.next()) {
            if(rs.getString(1).contains(inputEmail)) {
                HelloApplication.email = rs.getString(1);
                System.out.println(HelloApplication.email);
                String currencyString[] = rs.getString(2).trim().replace("{", "").replace("}", "").split(",");
                for (String currency:currencyString) {
                    String currencyIdk[] = currency.trim().split("=");
                    HelloApplication.currencyMap.put(currencyIdk[0], Boolean.valueOf(currencyIdk[1]));
                }
                return;
            }
        }

        currencyList.put("CZK", true);
        currencyList.put("EURO", false);
        currencyList.put("USD", true);

        rs.moveToInsertRow();
        rs.updateString("EMAIL", inputEmail);
        rs.updateString("FOLLOWEDCURRENCIES", String.valueOf(currencyList));
        rs.insertRow();
        new Csv().write("data/users.csv", rs, null);

        //create acc
        rs.close();
    }
}
