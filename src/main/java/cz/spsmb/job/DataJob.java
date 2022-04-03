package cz.spsmb.job;

import cz.spsmb.database.Database;
import cz.spsmb.entity.CurrencyEntity;
import cz.spsmb.gui.MainController;
import cz.spsmb.service.data.CSOBDataConvertor;
import cz.spsmb.service.data.SimpleDataFetcher;
import cz.spsmb.service.http.WebsiteCheck;
import cz.spsmb.service.mail.FinalSendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DataJob extends Thread {

    private static InputStream input = DataJob.class.getClassLoader().getResourceAsStream("config.properties");
    private static Logger logger = LogManager.getLogger(DataJob.class);

    private volatile CSOBDataConvertor csobData = new CSOBDataConvertor();
    private volatile Database database = new Database();
    private volatile List<CurrencyEntity> currencyList;
    private volatile List<CurrencyEntity> lastCurrencyList;
    private volatile boolean isRunning = true;
    private volatile boolean isDebugMode = false;
    private volatile int loopCounter = 0;
    private volatile String mail = "";

    private MainController stage;

    public MainController getStage() {
        return stage;
    }

    public void setStage(MainController stage) {
        this.stage = stage;
    }

    @Override
    public void run()  {
        try {
            database.startDBServer();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Tried to load config.properties");
        }

        do {
            loopCounter++;
            logger.debug("DataJob[" + loopCounter + "] (next loop in " + Double.parseDouble(prop.getProperty("app.timer.minutes")) + " minutes (" + Double.parseDouble(prop.getProperty("app.timer.minutes")) * 60000 + " ms))");
            String url = prop.getProperty("app.site.csob");

            if (WebsiteCheck.isAccessable(url, Integer.parseInt(prop.getProperty("web.timeout")))) {
                currencyList = csobData.convert(new SimpleDataFetcher().getContent(url));
                database.addCurrencies(currencyList);
                stage.updateCurrencies();

                /* Mail Logic */
                StringBuilder response = new StringBuilder();
                if (loopCounter > 1) {
                    for (int i = 0; i < currencyList.size(); i++) {
                        double currentPrice = currencyList.get(i).getCurrencyPrice();
                        double lastPrice = lastCurrencyList.get(i).getCurrencyPrice();
                        String currencyCode = currencyList.get(i).getCurrencyCode();
                        if (currentPrice < lastPrice) {
                            response.append(currencyCode).append(" dropped from ").append(lastPrice).append(" CZK to ").append(currentPrice).append(" CZK\r\n");
                        } else if (currentPrice > lastPrice) {
                            response.append(currencyCode).append(" jumped from ").append(lastPrice).append(" CZK to ").append(currentPrice).append(" CZK\r\n");
                        }
                    }
                    lastCurrencyList.clear();
                }

                lastCurrencyList = csobData.convert(new SimpleDataFetcher().getContent(url));
                currencyList.clear();
                if (response.length() != 0) {
                    FinalSendMail.send(mail, prop.getProperty("mail.subject"), response.toString());
                }
            }

            try {
                Thread.sleep((long) (60000 * Double.parseDouble(prop.getProperty("app.timer.minutes"))));
            } catch (InterruptedException e) {
                logger.error("Loop interrupted: " + e);
            }
        } while (isRunning);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isDebugMode() {
        return isDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        isDebugMode = debugMode;
    }

    public List<CurrencyEntity> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyEntity> currencyList) {
        this.currencyList = currencyList;
    }

    public int getLoopCounter() {
        return loopCounter;
    }

    public void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
