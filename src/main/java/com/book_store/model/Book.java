package com.book_store.model;

import java.util.List;

public class Book {
    private String ISBN;
    private String title;
    private String publisher;
    private String publication_year;
    private int selling_price;
    private String category;
    private int threshold;
    private int copies;
    List<String> authors;

    public Book(String ISBN, String title, String publisher, String publication_year,
                int selling_price, String category, int threshold, int copies, List<String> authors) {
        this.ISBN = ISBN;
        this.title = title;
        this.publisher = publisher;
        this.publication_year = publication_year;
        this.selling_price = selling_price;
        this.category = category;
        this.threshold = threshold;
        this.copies = copies;
        this.authors = authors;
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

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public int getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(int selling_price) {
        this.selling_price = selling_price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publication_year='" + publication_year + '\'' +
                ", selling_price=" + selling_price +
                ", category='" + category + '\'' +
                ", threshold=" + threshold +
                ", copies=" + copies +
                ", authors=" + authors +
                '}';
    }
}
