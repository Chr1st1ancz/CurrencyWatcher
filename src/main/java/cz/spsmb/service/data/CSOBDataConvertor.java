package cz.spsmb.service.data;

import cz.spsmb.entity.BankCode;
import cz.spsmb.entity.CurrencyEntity;
import cz.spsmb.gui.window.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSOBDataConvertor implements DataConvertor {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public List<CurrencyEntity> convert(String content) {
        List<CurrencyEntity> currencyEntities = new ArrayList<>();
        String[] contentArray = content.split("\n");
        for (int i = 4; i < contentArray.length; i++) {
            String[] dataArray = contentArray[i].split(";");
            // System.out.println(dataArray[2] + "; " + dataArray[1] + "; " + Double.parseDouble(dataArray[6].replace(',', '.')));
            try {
                // Mázl říká že tam má být prázdný konstruktor a setovat to setterama
                CurrencyEntity currencyEntity = new CurrencyEntity();
                currencyEntity.setCurrencyPrice(Double.parseDouble(dataArray[6].replace(',', '.'))/Integer.parseInt(dataArray[1]));
                currencyEntity.setCurrencyCode(dataArray[2]);
                currencyEntity.setBankCode(BankCode.CSOB);
                currencyEntity.setCurrencyPriceDate(new Date());
                currencyEntities.add(currencyEntity);
            } catch (Exception e) {
                logger.error("Site error: " + e);
                Window.errorBox(e.toString(), "Page does not contain required data\nTry checking out if the site is written correctly", this.getClass().toString());
                System.exit(0);
            }
        }
        logger.info("Success");
        currencyEntities.get(1).getCurrencyCode();
        currencyEntities.get(1).getCurrencyPrice();
        return currencyEntities;
    }
}
