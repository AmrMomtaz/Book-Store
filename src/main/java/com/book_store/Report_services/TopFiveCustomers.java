package com.book_store.Report_services;

public class TopFiveCustomers {
    private int ID;
    private String username;
    private String email;
    private String phonenumber;
    private String shipping_address;
    private int purchase_quantity;
    private int price;

    public TopFiveCustomers(int ID, String username, String email, String phonenumber, String shipping_address, int purchase_quantity,int price) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.shipping_address = shipping_address;
        this.purchase_quantity = purchase_quantity;
        this.price=price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public int getPurchase_quantity() {
        return purchase_quantity;
    }

    public void setPurchase_quantity(int purchase_quantity) {
        this.purchase_quantity = purchase_quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
