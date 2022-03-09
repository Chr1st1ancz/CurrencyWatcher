package cz.spsmb.service.data;

import cz.spsmb.entity.BankCode;
import cz.spsmb.entity.CurrencyEntity;

import java.util.ArrayList;
import java.util.List;

public class CSOBDataConvertor implements DataConvertor {
    @Override
    public List<CurrencyEntity> convert(String content) {
        List<CurrencyEntity> currencyEntities = new ArrayList<>();
        String[] contentArray = content.split("\n");
        for (int i = 4; i < contentArray.length; i++) {
            String[] dataArray = contentArray[i].split(";");
            System.out.println(dataArray[2] + "; " + dataArray[1] + "; " + Double.parseDouble(dataArray[6].replace(',', '.')));
            currencyEntities.add(new CurrencyEntity(BankCode.CSOB, dataArray[2], Integer.parseInt(dataArray[1]), Double.parseDouble(dataArray[6].replace(',', '.'))));
        }
        currencyEntities.get(1).getCurrencyCode();
        currencyEntities.get(1).getCurrencyPrice();
        return currencyEntities;
    }
}
