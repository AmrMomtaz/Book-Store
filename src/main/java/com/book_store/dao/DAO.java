package com.book_store.dao;

import com.book_store.Report_services.BookSales;
import com.book_store.Report_services.TopFiveCustomers;
import com.book_store.model.*;

import java.util.List;

public interface DAO {

    /**
     * Login functions and signup
     */
    // It returns the user if it exists and null if the data entered is wrong (used in login)
    User login(String email,String password);

    // Returns true if the email already exist in the DB (used in signing up)
    boolean checkEmailExists(String email);

    // It creates the user record in the database and returns 1 if the insertion succeeded (used in signing up)
    int createUser(User newUser);

    // Updates user information
    int updateUser(User user);

    /**
     *  Books functions (creation, update, delete)
     */

    // Creates the book in the DB and returns 1 if the insertion succeeded
    int createBook(Book newBook);

    // Updates the book in the DB and returns 1 if the update succeeded
    int updateBook(String ISBN, Book newBook);

    // Delete the book in the DB and returns 1 if the deletion succeeded
    int deleteBook(String ISBN);

    /**
     *  Search function for books
     */
    List<Book> listBooks(int pageSize , int pageNumber); // List all books given the page size and number
    List<Book> searchBookByISBN(String ISBN, int pageSize , int pageNumber);
    List<Book> searchBookByTitle(String title , int pageSize , int pageNumber);
    List<Book> searchBookByPublisher(String publisher, int pageSize , int pageNumber);
    List<Book> searchBookByPublication_year(String publication_year, int pageSize , int pageNumber);
    List<Book> searchBookByCategory(String category, int pageSize , int pageNumber);
    List<Book> searchBooksByAuthor(String author, int pageSize , int pageNumber);
    List<BookSales>bookSalesPrevMonth();
    List<BookSales>topTenBooks();
    List<TopFiveCustomers>topFiveCustomers();
    /**
     *  Handling shopping cart table
     */

    // Creates item in the shopping cart
    int addItemInShoppingCart(ShoppingCart newItem);

    // List all the items of specific user in the shopping cart
    List<ShoppingCart> listItemsInShoppingCart(int userID , int pageSize , int pageNumber);

    // Confirm purchase by User
    int confirmPurchase(CreditCard creditCard, int userID);

    // Deletes item from the cart
    int deleteCartItem(int userID,String ISBN);

    // User Log out
    int userLogout(int userID);

    /**
     * Administrator functions
     */

    // This function returns a lost of customers
    List<User> listCustomers(int pageSize , int pageNumber);

    // This function promote users
    int promoteUser(int userID);
    

    /**
     * Other functions (utils functions)
     */
    // Deletes user by ID
    int deleteUser(int ID);

    // Gets book by ISBN
    Book getBookByISBN(String ISBN);

    // Create and delete publisher
    int createPublisher(Publisher newPublisher);
    int deletePublisher(String publisherName);

    // Create and delete credit card
    int createCreditCard(CreditCard creditCard);
    int deleteCreditCard(String number);

    // gets all the authors of a specific book ISBN
    List<String> getAuthors(String ISBN);

    // Get books price
    Integer getBookPrice(String ISBN);
}
