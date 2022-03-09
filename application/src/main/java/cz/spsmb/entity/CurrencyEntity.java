package main.java.cz.spsmb.entity;

public class CurrencyEntity {

    private BankCode bankCode;
    private String currencyCode;
    private double currencyPrice;

    public CurrencyEntity(BankCode bankCode, String currencyCode, int currencyRatio, double currencyPrice) {
        this.bankCode = bankCode;
        this.currencyCode = currencyCode;
        this.currencyPrice = currencyPrice / currencyRatio;
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
}
