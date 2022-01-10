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
}
