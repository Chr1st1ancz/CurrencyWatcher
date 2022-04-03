package cz.spsmb.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CurrencyEntity")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "currency_id")
    private long id;

    @Column(name = "bank_code")
    private BankCode bankCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "currency_price")
    private double currencyPrice;

    @Column(name = "currency_price_date")
    private Date currencyPriceDate;

    public CurrencyEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BankCode getBankCode() {
        return bankCode;
    }

    public void setBankCode(BankCode bankCode) {
        this.bankCode = bankCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getCurrencyPrice() {
        return currencyPrice;
    }

    public void setCurrencyPrice(double currencyPrice) {
        this.currencyPrice = currencyPrice;
    }

    public Date getCurrencyPriceDate() {
        return currencyPriceDate;
    }

    public void setCurrencyPriceDate(Date currencyPriceDate) {
        this.currencyPriceDate = currencyPriceDate;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "id=" + id +
                ", bankCode=" + bankCode +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyPrice=" + currencyPrice +
                ", currencyPriceDate=" + currencyPriceDate +
                '}';
    }
}
