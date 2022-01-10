package com.book_store.model;

public class ShoppingCart {
    int userID;
    String ISBN;
    int count;
    int price;

    public ShoppingCart(int userID, String ISBN, int count) {
        this.userID = userID;
        this.ISBN = ISBN;
        this.count = count;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "userID=" + userID +
                ", ISBN='" + ISBN + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
