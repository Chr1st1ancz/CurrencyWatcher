package com.example.currencywatcher.entity;

import org.h2.engine.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class CurrencyCodeEntity {

    @Id
    @Column(name = "currency_id")
    private long id;

    private String code;

    @ManyToMany(mappedBy = "watchedCurrencies")
    private List<UserEntity> userEntityList;

}
