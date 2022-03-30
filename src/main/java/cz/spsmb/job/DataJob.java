package cz.spsmb.job;

import cz.spsmb.entity.CurrencyEntity;
import cz.spsmb.service.data.CSOBDataConvertor;
import cz.spsmb.service.data.SimpleDataFetcher;
import cz.spsmb.service.mail.FinalSendMail;
import cz.spsmb.service.mail.SimpleMailService;
import cz.spsmb.tests.MailTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class DataJob extends Thread {

    private static InputStream input = MailTest.class.getClassLoader().getResourceAsStream("config.properties");
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

    @Override
    public void run()  {
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("tried to load config.properties");
        }

        int loopCounter = 0;

        do {
            loopCounter++;
            logger.debug("looper[" + loopCounter + "] (next loop in " + Double.parseDouble(prop.getProperty("app.timer.minutes")) + " minutes)");

            /* Main Loop */
            currencyList = csobData.convert(new SimpleDataFetcher().getContent("https://www.csob.cz/portal/lide/kurzovni-listek-old/-/date/kurzy.txt"));
            FinalSendMail.send("gg.polacek@gmail.com", prop.getProperty("mail.subject"), listConvert(currencyList));

            /* Looper Timer */
            try {
                Thread.sleep((long) (60000 * Double.parseDouble(prop.getProperty("app.timer.minutes"))));
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("tried to loop");
            }
        } while (isRunning);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public List<CurrencyEntity> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<CurrencyEntity> currencyList) {
        this.currencyList = currencyList;
    }
}
