package com.book_store.dao;

import com.book_store.model.Book;
import com.book_store.model.User;

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
     * Other functions (Mainly for testing)
     */
    // Deletes user by ID
    int deleteUser(int ID);

    // Gets book by ISBN
    Book getBookByISBN(String ISBN);
}
