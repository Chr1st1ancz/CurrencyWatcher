package com.example.currencywatcher.database;

import com.example.currencywatcher.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

import static com.example.currencywatcher.HelloApplication.user;

public class Database {
    private Session session;

    public Database() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void startDBServer() throws SQLException {
        user = loginUser("john.jones@abcde.abc");
    }

    public UserEntity createUser(String inputEmail) {
        Transaction transaction = null;
        UserEntity newUser = null;
        try {
            transaction = session.beginTransaction();
            newUser = new UserEntity();
            newUser.setEmail(inputEmail);
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
           if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return newUser;
    }

    public UserEntity loginUser(String inputEmail) throws SQLException {
        Transaction transaction = null;
        try {
            List<UserEntity> users = session.createQuery("from UserEntity", UserEntity.class).list();
            for(UserEntity user : users) {
              if(user.getEmail().compareTo(inputEmail) == 0){
                  System.out.println("Přihlášen uživatel " + inputEmail + ".");
                  return user;
              }
            }

            return createUser(inputEmail);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
