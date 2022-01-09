package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DaoTests {

    DAO dao;

    @Autowired
    public DaoTests(DAO dao) {
        this.dao = dao;
    }

    @Test
    @DisplayName("Login functions test")
    void loginTests(){
        User test = new User("t","t","t","t",
                "t","t","t");
        Assertions.assertEquals(1,dao.create(test));
        Assertions.assertTrue(dao.checkEmailExists("t"));
        System.out.println(dao.login("t","t"));
        Assertions.assertEquals(1,dao.deleteUser(test.getID()));
    }
}
