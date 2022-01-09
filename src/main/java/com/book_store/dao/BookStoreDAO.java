package com.book_store.dao;

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
    public int create(User newUser) {
        int newID = getNewID();
        newUser.setID(newID);
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?)";
        int insert = jdbcTemplate.update(sql,newID,newUser.getUsername(),newUser.getPassword(),newUser.getFirst_name(),
                newUser.getLast_name(),newUser.getEmail(),newUser.getPhonenumber(),
                newUser.getShipping_address(),"customer");
        if(insert == 1)
            log.info("New user added to DB: " + newUser);
        return insert;
    }

    @Override
    public int deleteUser(int ID) {
        String sql = "DELETE FROM users WHERE id = ?";
        int update = jdbcTemplate.update(sql,ID);
        if(update==1)
            log.info("User deleted successfully (id="+ ID + ")");
        return update;
    }


}
