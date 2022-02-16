package com.example.currencywatcher.database;
import java.sql.*;
import java.util.List;

import com.example.currencywatcher.entity.UserEntity;
import org.h2.tools.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static com.example.currencywatcher.HelloApplication.currencyMap;

public class Database {
    private static String currencyString;
    public static Server server;
    public static Connection connection;
    public static Statement statement;

    public static void startDBServer() throws SQLException {
        server = Server.createTcpServer().start();
        connection = DriverManager.getConnection("jdbc:h2:mem:");
        statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );

        System.out.println("Databáze je " + server.getStatus() + ".");

        currencyMap.put("CZK", true);
        currencyMap.put("EURO", false);
        currencyMap.put("USD", true);

        createUser("bob.meier@abcde.abc");
        createUser("john.jones@abcde.abc");
        createUser("kokot@negr.lol");
    }

    public static void createUser(String inputEmail) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(new UserEntity());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
           if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void loginUser(String inputEmail) throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<UserEntity> users = session.createQuery("from UserEntity", UserEntity.class).list();
            users.forEach(s -> System.out.println(s.getEmail()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
