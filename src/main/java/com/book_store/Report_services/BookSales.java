package com.book_store.Report_services;

public class BookSales {
    String ISBN;
    String title;
    String publisher;
    int selling_price;
    int sold_quantity;

    public BookSales(String ISBN, String title, String publisher, int selling_price, int sold_quantity) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.selling_price = selling_price;
        this.sold_quantity = sold_quantity;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public int getSold_quantity() {
        return sold_quantity;
    }

    public void setSold_quantity(int sold_quantity) {
        this.sold_quantity = sold_quantity;
    }
}
