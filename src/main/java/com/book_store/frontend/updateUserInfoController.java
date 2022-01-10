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

public class updateUserInfoController {
    @FXML
    public PasswordField password;
    @FXML
    public Label errorMessage;
    @FXML
    public TextField username,phoneNumber, shippingLocation, firstName , lastName , email;

    private User user;
    
    @FXML
    
    public void setUser(User user) {
        this.user = user;
    }
    public void start(){
        if(user!=null){
            username.setText(user.getUsername());
            phoneNumber.setText(user.getPhonenumber());
            shippingLocation.setText(user.getShipping_address());
            firstName.setText(user.getFirst_name());
            lastName.setText(user.getLast_name());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
    }
    @FXML
    public void saveChanges(ActionEvent event) throws IOException {
        if(validateFields()){
            errorMessage.setText("please fill all fields");
        }
        else if(!email.getText().equals(user.getEmail()) && frontEndDAO.dao.checkEmailExists(email.getText())){
            errorMessage.setText("an account with the same email already exists");
        }
        else{
            /*User user = new User(
                    username.getText(),
                    password.getText(),
                    firstName.getText(),
                    lastName.getText(),
                    email.getText(),
                    phoneNumber.getText(),
                    shippingLocation.getText());*/
            user.setUsername(username.getText());
            user.setPassword(password.getText());
            user.setFirst_name(firstName.getText());
            user.setLast_name(lastName.getText());
            user.setEmail(email.getText());
            user.setPhonenumber(phoneNumber.getText());
            user.setShipping_address(shippingLocation.getText());

            frontEndDAO.dao.updateUser(user);
            System.out.println(user);
            //this.user = user;
            errorMessage.setText("all good");
            SwitchToWelcomeScreen(event);
        }
    }
    private void SwitchToWelcomeScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcome.fxml"));
        Parent root = loader.load();
        WelcomeScreenController welcomeController = loader.getController();
        welcomeController.setUser(user);
        welcomeController.start();
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
