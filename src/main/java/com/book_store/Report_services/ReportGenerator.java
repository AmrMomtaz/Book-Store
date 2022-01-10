package com.book_store.Report_services;

import com.book_store.dao.BookStoreDAO;
import com.book_store.model.Book;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReportGenerator {


    BookStoreDAO bookStoreDAO;

    public ReportGenerator(BookStoreDAO bookStoreDAO) {
        this.bookStoreDAO = bookStoreDAO;
    }

    public String generateBookSalesReport() throws FileNotFoundException, JRException {
        List<BookSales>books=bookStoreDAO.bookSalesPrevMonth();
        File file= ResourceUtils.getFile("classpath:BookSales.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(books);
        Map<String,Object> map=new HashMap<>();
        map.put("Created by","MoRadwan");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,map,dataSource) ;
        JasperExportManager.exportReportToPdfFile(jasperPrint,"./BookSalesPrevMonth.pdf");
        return "Success";
    }
    public String generateTopTenBookSalesReport() throws FileNotFoundException, JRException {
        List<BookSales>books=bookStoreDAO.topTenBooks();
        File file= ResourceUtils.getFile("classpath:BookSales.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(books);
        Map<String,Object> map=new HashMap<>();
        map.put("Created by","MoRadwan");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,map,dataSource) ;
        JasperExportManager.exportReportToPdfFile(jasperPrint,"./TopTenBookSales.pdf");
        return "Success";
    }
    public String generateFiveCustomersReport() throws FileNotFoundException, JRException {
        List<TopFiveCustomers>books=bookStoreDAO.topFiveCustomers();
        File file= ResourceUtils.getFile("classpath:CustomerReport.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(books);
        Map<String,Object> map=new HashMap<>();
        map.put("Created by","MoRadwan");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,map,dataSource) ;
        JasperExportManager.exportReportToPdfFile(jasperPrint,"./TopFiveCustomers.pdf");
        return "Success";
    }
}
