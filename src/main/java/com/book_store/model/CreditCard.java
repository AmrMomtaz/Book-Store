package com.book_store.model;

public class CreditCard {
    String number;
    String expire_date;

    public CreditCard(String number, String expire_date) {
        this.number = number;
        this.expire_date = expire_date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", expire_date='" + expire_date + '\'' +
                '}';
    }
}
