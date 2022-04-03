package cz.spsmb.database;

import cz.spsmb.entity.CurrencyEntity;
import cz.spsmb.service.data.CSOBDataConvertor;
import cz.spsmb.service.data.SimpleDataFetcher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.*;

public class Database {
    private Session session;

    public Database() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public void startDBServer() throws SQLException {
        addCurrencies(new CSOBDataConvertor().convert(new SimpleDataFetcher().getContent("https://www.csob.cz/portal/lide/kurzovni-listek-old/-/date/kurzy.txt")));
    }

    public List<CurrencyEntity> getAllCurrencies(){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            List<CurrencyEntity> existingCurrencies = session.createQuery("from CurrencyEntity", CurrencyEntity.class).list();
            transaction.commit();

            return existingCurrencies;
        } catch (Exception e) {
        System.err.println(e);
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }

        List<CurrencyEntity> existingCurrencies = new ArrayList<>();
        return existingCurrencies;
    }

    public void addCurrencies(List<CurrencyEntity> newCurrencies) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            for (int i = 0; i < newCurrencies.size(); i++) {
                CurrencyEntity curr = newCurrencies.get(i);
                System.out.println("Přidávám měnu " + curr.getCurrencyCode());
                session.save(curr);
            }
            System.out.println("Nové měny uloženy.");

            transaction.commit();
        } catch (Exception e) {
            System.err.println(e);
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<CurrencyEntity> findCurrencyFromList(List<CurrencyEntity> list, String toFind){
        List<CurrencyEntity> found = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getCurrencyCode().toUpperCase(Locale.ROOT) == toFind.toUpperCase(Locale.ROOT)){
                found.add(list.get(i));
            }
        }

        return found;
    }
}