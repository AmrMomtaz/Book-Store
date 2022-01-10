package com.book_store.frontend;

import com.book_store.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class usersController {
    @FXML
    public ListView usersList;
    @FXML
    public Label pageNumber;
    @FXML
    public AnchorPane orderContainer;
    @FXML
    public Label email;
    @FXML
    public Label username;
    private User user;
    private final int pageSize = 10;
    private List<User> users;
    private User selectedUser;
    public void setUser(User user) {
        this.user = user;
    }
    public void start(){
        users = frontEndDAO.dao.listCustomers(pageSize,Integer.parseInt(pageNumber.getText()));
        usersList.setStyle("-fx-font-size: 1.3em; -fx-font-style: italic; -fx-font-weight: bold;");
        users = frontEndDAO.dao.listCustomers(pageSize,Integer.parseInt(pageNumber.getText()));
        usersList.getItems().addAll(usersListToArray());
        usersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index = usersList.getSelectionModel().getSelectedIndex();
                if(users.size()>index && index>=0){
                    selectedUser = users.get(index);
                    username.setText(selectedUser.getUsername());
                    email.setText(selectedUser.getEmail());
                }
            }
        });
    }
    private String[] usersListToArray() {
        String[] userNames = new String[10];
        if(users!=null) {
            for (int i = 0; i < users.size() && i < 10; i++) {
                userNames[i] = users.get(i).getEmail();
            }
        }
        for(int i=0; i<10 ; i++){
            if(userNames[i] ==null)
                userNames[i]= "";
        }
        return userNames;
    }
    public void next(ActionEvent event) {
        if(users.size()>0){
            int page = Integer.parseInt(pageNumber.getText())+1;
            pageNumber.setText(Integer.toString(page));
            users = frontEndDAO.dao.listCustomers(pageSize, page);
            usersList.getItems().remove(0,10);
            usersList.getItems().addAll(usersListToArray());
        }
    }

    public void previous(ActionEvent event) {
        if(Integer.parseInt(pageNumber.getText())>1){
            int page = Integer.parseInt(pageNumber.getText())-1;
            pageNumber.setText(Integer.toString(page));
            users = frontEndDAO.dao.listCustomers(pageSize, page);
            usersList.getItems().remove(0,10);
            usersList.getItems().addAll(usersListToArray());
        }
    }

    public void logout(ActionEvent event) throws IOException {
        frontEndDAO.dao.userLogout(user.getID());
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void promoteUser(ActionEvent event) {
        frontEndDAO.dao.promoteUser(selectedUser);
        System.out.println(selectedUser);
        users = frontEndDAO.dao.listCustomers(pageSize, Integer.parseInt(pageNumber.getText()));
        usersList.getItems().remove(0,10);
        usersList.getItems().addAll(usersListToArray());
    }

    public void homePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcome.fxml"));
        Parent root = loader.load();
        WelcomeScreenController welcomeController = loader.getController();
        welcomeController.setUser(user);
        welcomeController.start();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
