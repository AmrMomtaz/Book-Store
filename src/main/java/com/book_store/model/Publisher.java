package com.book_store.model;

public class Publisher {

    private final String name;
    private final String address;
    private final String phonenumber;

    public Publisher(String name, String address, String phonenumber) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
