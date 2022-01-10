package com.book_store.frontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {

    @FXML
    private ListView<String> booksList;
    private String[] books = {"topological" , "test" , "test" ,"test" , "test",
            "test" , "test" ,"test" , "test" , "test"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        booksList.setStyle("-fx-font-size: 1.3em; -fx-font-style: italic; -fx-font-weight: bold;");
        booksList.getItems().addAll(books);
        booksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

            }
        });
    }

    public void next(ActionEvent event) {
    }

    public void previous(ActionEvent event) {
    }

    public void search(ActionEvent event) {
    }

    public void logout(ActionEvent event) {
    }

    public void addToCart(ActionEvent event) {
    }

    public void reportAnalysis(ActionEvent event) {
    }

    public void addBook(ActionEvent event) {
    }

    public void showUsers(ActionEvent event) {
    }

    public void modifyBook(ActionEvent event) {
    }

    public void checkout(ActionEvent event) {
    }

    public void confirmOrders(ActionEvent event) {
    }
}
