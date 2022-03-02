package main.java.cz.spsmb.service;

import main.java.cz.spsmb.entity.CurrencyEntity;

import java.util.List;

public class CSOBDataConvertor implements DataConvertor {
    @Override
    public List<CurrencyEntity> convert(String content) {
        List<CurrencyEntity> currencyEntities;
        Object[] contentArray = content.lines().toArray();
        for (int i = 4; i < contentArray.length; i++) {
            System.out.println(contentArray[i]);
            currencyEntities.add(new CurrencyEntity("CSOB", contentArray[i]., "", ""));
        }
        return null;
    }
}
