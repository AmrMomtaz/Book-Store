package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.frontend.Main;
import com.book_store.frontend.frontEndDAO;
import javafx.application.Application;
import com.book_store.Report_services.ReportGenerator;
import com.book_store.dao.BookStoreDAO;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class BookStoreApplication {
    private static BookStoreDAO dbo;
    private static ReportGenerator reportGenerator;

    public static DAO dao;

    public BookStoreApplication(DAO Dao){
        dao = Dao;
    }

    public static void main(String[] args) throws JRException, FileNotFoundException {
       /* */
        SpringApplication.run(BookStoreApplication.class, args);
        frontEndDAO.dao = dao;
        reportGenerator=new ReportGenerator(dao);
        reportGenerator.generateBookSalesReport();
        reportGenerator.generateTopTenBookSalesReport();
        reportGenerator.generateFiveCustomersReport();
        Application.launch(Main.class,args);

        System.out.println(dao);

       //

    }

}
