package cz.spsmb.job;

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
import java.util.List;
import java.util.Properties;

public class DataJob extends Thread {

    private static InputStream input = DataJob.class.getClassLoader().getResourceAsStream("config.properties");
    private static Logger logger = LogManager.getLogger(DataJob.class);

    private volatile CSOBDataConvertor csobData = new CSOBDataConvertor();
    private volatile List<CurrencyEntity> currencyList;
    private String listConvert(List<CurrencyEntity> list) {
        String returned = "";
        for (CurrencyEntity currencyEntity : list) {
            returned += "\r\n" + currencyEntity.getCurrencyCode() + " : " + currencyEntity.getCurrencyPrice();
        }
        return returned;
    }

    private volatile boolean isRunning = true;
    private volatile boolean isDebugMode = false;
    private MainController stage;

    public MainController getStage() {
        return stage;
    }

    public void setStage(MainController stage) {
        this.stage = stage;
    }

    @Override
    public void run()  {
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Tried to load config.properties");
        }

        int loopCounter = 0;
        do {
            loopCounter++;
            logger.debug("DataJob[" + loopCounter + "] (next loop in " + Double.parseDouble(prop.getProperty("app.timer.minutes")) + " minutes (" + Double.parseDouble(prop.getProperty("app.timer.minutes")) * 60000 + " ms))");
            String url = prop.getProperty("app.site.csob");
            if (WebsiteCheck.isAccessable(url, Integer.parseInt(prop.getProperty("web.timeout")))) {
                currencyList = csobData.convert(new SimpleDataFetcher().getContent(url));
                stage.updateCurrencies();
                currencyList.clear();
                if (!isDebugMode) {
                    FinalSendMail.send("gg.polacek@gmail.com", prop.getProperty("mail.subject"), listConvert(currencyList));
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
}
