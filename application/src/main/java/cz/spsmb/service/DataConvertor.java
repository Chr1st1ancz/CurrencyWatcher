package main.java.cz.spsmb.service;

import main.java.cz.spsmb.entity.CurrencyEntity;

import java.util.List;

public interface DataConvertor {

    List<CurrencyEntity> convert(String content);

}
