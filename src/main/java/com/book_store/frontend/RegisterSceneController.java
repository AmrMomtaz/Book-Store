package com.book_store.frontend;

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

import java.io.IOException;

public class RegisterSceneController {

    @FXML
    private Label errorMessage;
    @FXML
    private TextField firstName,lastName,username,email,phoneNumber,shippingLocation;
    @FXML
    private PasswordField password;


    @FXML
    public void CreateAccount(ActionEvent event){
        if(invalidateFields()){
            errorMessage.setText("please fill all fields");
        }
        else if(frontEndDAO.dao.checkEmailExists(email.getText())){
            errorMessage.setText("an account with the same email already exists");
        }
        else{
            User user = new User(
                    username.getText(),
                    password.getText(),
                    firstName.getText(),
                    lastName.getText(),
                    email.getText(),
                    phoneNumber.getText(),
                    shippingLocation.getText());
            frontEndDAO.dao.createUser(user);
            System.out.println(user);
            errorMessage.setText("all good");
        }
    }
    @FXML
    public void SwitchToLoginScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean invalidateFields(){
        return  firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() ||
                username.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phoneNumber.getText().isEmpty() ||
                shippingLocation.getText().isEmpty()||
                password.getText().isEmpty();
    }
}
