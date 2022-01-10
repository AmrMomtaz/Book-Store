package com.book_store.Report_services;

public class TopFiveCustomers {
    private int ID;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String phonenumber;
    private String shipping_address;
    private String purchase_quantity;

    public TopFiveCustomers(int ID, String username, String first_name, String last_name, String email, String phonenumber, String shipping_address, String purchase_quantity) {
        this.ID = ID;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.shipping_address = shipping_address;
        this.purchase_quantity = purchase_quantity;
    }
}
