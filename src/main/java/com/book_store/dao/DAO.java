package com.book_store.dao;

import com.book_store.model.User;

public interface DAO {

    /**
     * Login functions and signup
     */

    // login function: it returns the user if it exists and null if the data entered is wrong
    User login(String email,String password);

    // check email: returns true if the email already exist in the DB
    boolean checkEmailExists(String email);

    // sign up: It creates the user record in the database and returns 1 if the insertion succeeded
    int create(User newUser);

    // This function is used to delete user by ID
    int deleteUser(int ID);
}
