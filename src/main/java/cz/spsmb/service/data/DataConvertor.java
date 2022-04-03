package cz.spsmb.service.data;

import cz.spsmb.entity.CurrencyEntity;

import java.util.List;

public interface DataConvertor {

    List<CurrencyEntity> convert(String content);

}
