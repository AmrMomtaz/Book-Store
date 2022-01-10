package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.frontend.Main;
import com.book_store.frontend.frontEndDAO;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookStoreApplication {

    public static DAO dao;

    public BookStoreApplication(DAO Dao){
        dao = Dao;
    }

    public static void main(String[] args) {

        SpringApplication.run(BookStoreApplication.class, args);
        frontEndDAO.dao = dao;
        Application.launch(Main.class,args);
        System.out.println(dao);
    }

}
