package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.model.Book;
import com.book_store.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoTests {

    private DAO dao;

    @Autowired
    public DaoTests(DAO dao) {
        this.dao = dao;
    }

    @Test
    @DisplayName("Login functions test")
    void loginTests(){
        User test = new User("t","t","t","t",
                "t","t","t");
        Assertions.assertEquals(1,dao.createUser(test));
        Assertions.assertTrue(dao.checkEmailExists("t"));
        System.out.println(dao.login("t","t"));
        Assertions.assertEquals(1,dao.deleteUser(test.getID()));
    }

    @Test
    @DisplayName("Handling books")
    void booksTests(){
        Book test = new Book("t","t","t","t",
                100,"Science",50,200);
        Assertions.assertEquals(1,dao.createBook(test));
        System.out.println(dao.getBookByISBN("t"));
        test.setTitle("NEW TITLE");
        Assertions.assertEquals(1,dao.updateBook("t",test));
        System.out.println(dao.getBookByISBN("t"));
        Assertions.assertEquals(1,dao.deleteBook(test.getISBN()));
    }
}
