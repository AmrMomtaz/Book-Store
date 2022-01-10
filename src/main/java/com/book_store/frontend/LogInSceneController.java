package com.book_store.frontend;

import com.book_store.dao.BookStoreDAO;
import com.book_store.dao.DAO;
import com.book_store.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;


public class LogInSceneController {

    @FXML
    private Label errorMessage;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    public User user;

    @FXML
    public void logIn(ActionEvent event) throws IOException {
        if(email.getText().isEmpty())
            errorMessage.setText("Email field cannot be empty");
        else if(password.getText().isEmpty())
            errorMessage.setText("Password field cannot be empty");
        else{
           user = frontEndDAO.dao.login(email.getText(),password.getText());
           if(user == null){
               errorMessage.setText("Invalid username or password");
           }
           else{
               System.out.println(user);
               SwitchToWelcomeScene(event);
           }
        }
    }
    @FXML
    public void SwitchToRegisterScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    private void SwitchToWelcomeScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcome.fxml"));
        Parent root = loader.load();
        WelcomeScreenController welcomeController = loader.getController();
        System.out.println("in login: " +user);
        welcomeController.setUser(user);
        welcomeController.start();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
