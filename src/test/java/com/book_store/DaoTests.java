package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.model.Book;
import com.book_store.model.Publisher;
import com.book_store.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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
        Book test = new Book("t","t","PUBLISHER_TEST","t",
                100,"science",50,200, Arrays.asList("author1","author2","author3"));
        Assertions.assertEquals(1,dao.createPublisher(
                new Publisher("PUBLISHER_TEST","t","t")));
        Assertions.assertEquals(1,dao.createBook(test));
        System.out.println(dao.getBookByISBN("t"));
        test.setTitle("NEW TITLE");
        test.setAuthors(Arrays.asList("new author1","new author2"));
        Assertions.assertEquals(1,dao.updateBook("t",test));
        System.out.println(dao.getBookByISBN("t"));
        Assertions.assertEquals(1,dao.deleteBook(test.getISBN()));
        Assertions.assertEquals(1,dao.deletePublisher("PUBLISHER_TEST"));
    }
}
