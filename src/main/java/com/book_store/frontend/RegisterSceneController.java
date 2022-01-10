package com.book_store.frontend;

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
        if(validateFields()){
            errorMessage.setText("please fill all fields");
        }
        else{
            //take the corresponding action
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

    private boolean validateFields(){
        return  firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() ||
                username.getText().isEmpty() ||
                email.getText().isEmpty() ||
                phoneNumber.getText().isEmpty() ||
                shippingLocation.getText().isEmpty()||
                password.getText().isEmpty();
    }
}
