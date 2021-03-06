package com.book_store.dao;


import com.book_store.model.*;

import com.book_store.Report_services.BookSales;
import com.book_store.Report_services.TopFiveCustomers;
import com.book_store.model.Book;
import com.book_store.model.Publisher;
import com.book_store.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Repository("book_store_DAO")
public class BookStoreDAO implements DAO{

    private final JdbcTemplate jdbcTemplate;
    private static final Logger log = LoggerFactory.getLogger(BookStoreDAO.class);

    @Autowired
    public BookStoreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private int getNewID(){
        int id = -1;
        try {
            Scanner sc = new Scanner(new File("src/main/resources/index"));
            id = sc.nextInt();
            sc.close();
            FileWriter fileWriter = new FileWriter("src/main/resources/index");
            fileWriter.write(Integer.toString(id + 1));
            fileWriter.close();
        } catch(FileNotFoundException fileNotFoundException){
            System.err.println("ERROR ! " + "src/main/resources/index" + " is not found");
            System.exit(-1);
        } catch (IOException ioException) {
            System.err.println("ERROR ! " + "src/main/resources/index" + " is corrupted");
            System.exit(-1);
        }
        return id;
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum)-> {
        int ID = rs.getInt("ID");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String first_name = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        String email = rs.getString("email");
        String phonenumber = rs.getString("phonenumber");
        String shipping_address = rs.getString("shipping_address");
        String type = rs.getString("type");
        User newUser = new User(username,password,first_name
            ,last_name,email,phonenumber,shipping_address);
        newUser.setID(ID);
        newUser.setType(type);
        return newUser;
    };

    private final RowMapper<Book> bookRowMapper = (rs, rowNum)-> {
        String ISBN = rs.getString("ISBN");
        String title = rs.getString("title");
        String publisher = rs.getString("publisher");
        String publication_year = rs.getString("publication_year");
        int selling_price = rs.getInt("selling_price");
        String category = rs.getString("category");
        int threshold = rs.getInt("threshold");
        int copies = rs.getInt("copies");
        List<String> authors = getAuthors(ISBN);
        return new Book(ISBN,title,publisher,publication_year,selling_price,
                category,threshold,copies,authors);
    };

    private final RowMapper<BookSales> bookReportRowMapper = (rs, rowNum)-> {
        String ISBN = rs.getString("ISBN");
        String title = rs.getString("title");
        String publisher = rs.getString("publisher");
        int selling_price = rs.getInt("price");
        int sold_quantity= rs.getInt("count");
        return new BookSales(ISBN,title,publisher,selling_price,sold_quantity);
    };

    private final RowMapper<TopFiveCustomers> customerRowMapper = (rs, rowNum)-> {
        int ID = rs.getInt("ID");
        String username = rs.getString("username");
        String email = rs.getString("email");
        String phonenumber = rs.getString("phonenumber");
        String shipping_address = rs.getString("shipping_address");
        int price = rs.getInt("price");
        int purchase_quantity=rs.getInt("count");
        TopFiveCustomers customer = new TopFiveCustomers(ID,username,email,phonenumber,shipping_address,purchase_quantity,price);
        return customer;
    };
    public List<BookSales>bookSalesPrevMonth(){
        String sql="SELECT ISBN,title,publisher,sum(price)as price,sum(count)as count"+
        " FROM orders natural join books"+
        " WHERE  date BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()"+
        " group by ISBN";
        return jdbcTemplate.query(sql,bookReportRowMapper);
    }
    public List<BookSales>topTenBooks(){
        String sql="SELECT ISBN,title,publisher,sum(price)as price,sum(count)as count\n" +
                "FROM orders natural join books\n" +
                "WHERE  date BETWEEN DATE_SUB(NOW(), INTERVAL 90 DAY) AND NOW()\n" +
                "group by ISBN order by count desc\n" +
                "LIMIT 10\n";
        return jdbcTemplate.query(sql,bookReportRowMapper);
    }
    public List<TopFiveCustomers>topFiveCustomers(){
        String sql="SELECT user_ID as ID,username,email,phonenumber,shipping_address,sum(price)as price,sum(count)as count\n" +
                "FROM orders  join users ON(ID=user_ID)\n" +
                "WHERE  date BETWEEN DATE_SUB(NOW(), INTERVAL 90 DAY) AND NOW()\n" +
                "group by user_ID order by price desc\n" +
                "LIMIT 5 \n";
        return jdbcTemplate.query(sql,customerRowMapper);
    }

    @Override
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        User user = null;
        try {
            user = jdbcTemplate.queryForObject(sql, userRowMapper,email,password);
        }catch(DataAccessException dataAccessException) {
            log.error("User not found (email=" +email+",password="+password+")");
        }
        return user;
    }

    @Override
    public boolean checkEmailExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql,userRowMapper,email);
        return users.size() > 0;
    }

    @Override
    public int createUser(User newUser) {
        int newID = getNewID();
        newUser.setID(newID);
        newUser.setType("customer");
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,newID,newUser.getUsername(),newUser.getPassword(),newUser.getFirst_name(),
                newUser.getLast_name(),newUser.getEmail(),newUser.getPhonenumber(),
                newUser.getShipping_address(),"customer");
        if(insert == 1)
            log.info("New user added to DB: " + newUser);
        return insert;
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update("UPDATE users SET username = ? , password = ?," +
                "first_name = ? , last_name = ? , email = ? , phonenumber = ? , shipping_address = ? WHERE ID = ?",
                user.getUsername(),user.getPassword(),user.getFirst_name(),user.getLast_name(),user.getEmail(),
                user.getPhonenumber(),user.getShipping_address(),user.getID());
    }

    @Override
    public int createBook(Book newBook) {
        String sql = "INSERT INTO books VALUES (?,?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,newBook.getISBN(),newBook.getTitle(),newBook.getPublisher()
                    ,newBook.getPublication_year(),newBook.getSelling_price(),newBook.getCategory()
                    ,newBook.getThreshold(),newBook.getCopies());
        if (insert != 1){
            log.error("Insertion failed of book || ISBN " + newBook.getISBN());
            return insert;
        }
        for (String author : newBook.getAuthors()){
            sql = "INSERT INTO authors VALUES (?,?)";
            insert = jdbcTemplate.update(sql,newBook.getISBN(),author);
            if(insert != 1) {
                log.error("Couldn't add all authors for book || ISBN = " + newBook.getISBN());
                return insert;
            }
        }
        log.info("New book added to DB || ISBN : " + newBook.getISBN());
        return insert;
    }

    public List<Book>getAllBooks(){
        String sql= "SELECT * FROM books;";
        return jdbcTemplate.query(sql,bookRowMapper);
    }
    @Override
    public int updateBook(String ISBN, Book newBook) {
        String sql = "UPDATE books SET ISBN = ? , title = ?" +
                " , publisher = ? , publication_year = ? , selling_price = ?" +
                " , category = ? , threshold = ? , copies = ? WHERE ISBN = ?";
        int update = jdbcTemplate.update(sql,newBook.getISBN(),newBook.getTitle(),newBook.getPublisher()
                ,newBook.getPublication_year(),newBook.getSelling_price(),newBook.getCategory()
                ,newBook.getThreshold(),newBook.getCopies(),ISBN);
        if (update != 1) {
            log.error("Update failed of book || ISBN " + newBook.getISBN());
            return update;
        }
        sql = "DELETE from authors WHERE ISBN = ?";
        update = jdbcTemplate.update(sql,ISBN);
        for (String author : newBook.getAuthors()){
            sql = "INSERT INTO authors VALUES (?,?)";
            update = jdbcTemplate.update(sql,newBook.getISBN(),author);
            if(update != 1) {
                log.error("Couldn't update all authors for book || ISBN = " + newBook.getISBN());
                return update;
            }
        }
        if(update == 1)
            log.info("Book updated in DB || ISBN : " + newBook.getISBN());
        return update;
    }

    @Override
    public int deleteBook(String ISBN) {
        String sql = "DELETE FROM books WHERE ISBN = ?";
        int delete = jdbcTemplate.update(sql,ISBN);
        if(delete==1)
            log.info("Book deleted successfully (ISBN="+ ISBN + ")");
        return delete;
    }

    @Override
    public List<Book> listBooks(int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBookByISBN(String ISBN, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books WHERE ISBN LIKE ? LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,ISBN+"%" ,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBookByTitle(String title, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books WHERE title LIKE ? LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,title+"%" ,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBookByPublisher(String publisher, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books WHERE publisher LIKE ? LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,publisher+"%" ,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBookByPublication_year(String publication_year, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books WHERE publication_year LIKE ? LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,publication_year+"%" ,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBookByCategory(String category, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM books WHERE category LIKE ? LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,category+"%" ,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public List<Book> searchBooksByAuthor(String author, int pageSize, int pageNumber) {
        String sql = "SELECT * FROM  books AS B WHERE B.ISBN IN " +
                "(SELECT DISTINCT ISBN FROM authors AS A WHERE A.author_name LIKE ?)LIMIT ?,?";
        return jdbcTemplate.query(sql,bookRowMapper,author + "%", (pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public int addItemInShoppingCart(ShoppingCart newItem) {
        newItem.setPrice(getBookPrice(newItem.getISBN())*newItem.getCount());
        return jdbcTemplate.update("INSERT INTO  shopping_cart VALUES (?,?,?,?)"
        ,newItem.getUserID(),newItem.getISBN(),newItem.getCount(),newItem.getPrice());
    }

    @Override
    public List<ShoppingCart> listItemsInShoppingCart(int userID , int pageSize , int pageNumber) {
        String sql = "SELECT * FROM shopping_cart WHERE user_ID = ? LIMIT ?,?";
        return jdbcTemplate.query(sql,(rs,rowNum)->{
            int user_id = rs.getInt("user_ID");
            String ISBN = rs.getString("ISBN");
            int count = rs.getInt("count");
            int price = rs.getInt("price");
            ShoppingCart shoppingCart = new ShoppingCart(user_id,ISBN,count);
            shoppingCart.setPrice(price);
            return shoppingCart;
        },userID,(pageNumber-1)*pageSize,pageSize);
    }

    private List<ShoppingCart> listItemsInShoppingCart(int userID ) {
        String sql = "SELECT * FROM shopping_cart WHERE user_ID = ?";
        return jdbcTemplate.query(sql,(rs,rowNum)->{
            int user_id = rs.getInt("user_ID");
            String ISBN = rs.getString("ISBN");
            int count = rs.getInt("count");
            int price = rs.getInt("price");
            ShoppingCart shoppingCart = new ShoppingCart(user_id,ISBN,count);
            shoppingCart.setPrice(price);
            return shoppingCart;
        },userID);
    }
    @Override
    public int confirmPurchase(CreditCard creditCard, int userID) {
        CreditCard verifiedCard = null;
        try{
            verifiedCard = jdbcTemplate.queryForObject("SELECT * FROM credit_card WHERE " +
                            "number = ?" ,(rs,rowNum)-> new CreditCard(rs.getString("number"),rs.getString("date")),
                    creditCard.getNumber());
        }catch (Exception e){
            log.error("Invalid credit card || " + creditCard);
        }
        if(verifiedCard==null)
            return -1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        List<ShoppingCart> shoppingCartList = listItemsInShoppingCart(userID);
        int insert = 0;
        for (ShoppingCart item : shoppingCartList) {
            insert += jdbcTemplate.update("INSERT INTO orders VALUES (?,?,?,?,?)",
                    item.getUserID(), item.getISBN(), item.getCount(), item.getPrice(), dtf.format(now));
            Book book = getBookByISBN(item.getISBN());
            book.setCopies(book.getCopies()-item.getCount());
            updateBook(book.getISBN(),book);
        }
        userLogout(userID);
        return insert == shoppingCartList.size() ? 1 : 0;
    }

    @Override
    public int deleteCartItem(int userID, String ISBN) {
        return jdbcTemplate.update("DELETE FROM shopping_cart WHERE user_ID = ? AND ISBN = ?",userID,ISBN);
    }

    @Override
    public int userLogout(int userID) {
        return jdbcTemplate.update("DELETE FROM shopping_cart WHERE user_ID = ?" , userID);
    }

    @Override
    public List<User> listCustomers(int pageSize, int pageNumber) {
        return jdbcTemplate.query("SELECT * FROM users WHERE type = ? LIMIT ?,?",
                userRowMapper,"customer",(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public int promoteUser(User user) {
        user.setType("manager");
        return jdbcTemplate.update("UPDATE users SET type = ? WHERE ID = ?","manager",user.getID());
    }

    @Override
    public int deleteUser(int ID) {
        String sql = "DELETE FROM users WHERE id = ?";
        int delete = jdbcTemplate.update(sql,ID);
        if(delete==1)
            log.info("User deleted successfully (id="+ ID + ")");
        return delete;
    }

    @Override
    public Book getBookByISBN(String ISBN) {
        String sql = "SELECT * FROM books WHERE ISBN = ?";
        Book book = null;
        try {
            book = jdbcTemplate.queryForObject(sql,bookRowMapper,ISBN);
        }catch(DataAccessException dataAccessException) {
            log.error("Book not found (ISBN=" +ISBN+")");
        }
        return book;
    }

    @Override
    public int createPublisher(Publisher newPublisher) {
        return jdbcTemplate.update("INSERT INTO publishers VALUES (?,?,?)",
                newPublisher.getName(),newPublisher.getAddress(),newPublisher.getPhonenumber());
    }

    @Override
    public int deletePublisher(String publisherName) {
        return jdbcTemplate.update("DELETE FROM publishers WHERE name = ?", publisherName);
    }

    @Override
    public int createCreditCard(CreditCard creditCard) {
        return jdbcTemplate.update("INSERT INTO credit_card VALUES (?,?)",
                creditCard.getNumber(),creditCard.getExpire_date());
    }

    @Override
    public int deleteCreditCard(String number) {
        return jdbcTemplate.update("DELETE FROM credit_card WHERE number = ?", number);
    }

    @Override
    public List<String> getAuthors(String ISBN) {
        String sql = "SELECT author_name FROM authors WHERE ISBN = ?";
        return jdbcTemplate.query(sql,(rs, rowNum) -> rs.getString("author_name"),ISBN);
    }

    @Override
    public Integer getBookPrice(String ISBN) {
        String sql = "SELECT selling_price FROM books WHERE ISBN = ?";
        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> rs.getInt("selling_price"),ISBN);
    }
}