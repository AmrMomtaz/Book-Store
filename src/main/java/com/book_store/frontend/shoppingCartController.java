package com.book_store.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class shoppingCartController implements Initializable {

    @FXML
    AnchorPane bookInfoContainer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //bookInfoContainer.setOpacity(0);
        //bookInfoContainer.setDisable(true);
    }

    public void confirmOrder(ActionEvent event){

    }

    public void next(javafx.event.ActionEvent event) {
    }

    public void previous(javafx.event.ActionEvent event) {
    }

    public void logout(javafx.event.ActionEvent event) {
    }

    public void removeBook(javafx.event.ActionEvent event) {
    }

    public void homePage(javafx.event.ActionEvent event) {
    }

    public void checkOut(javafx.event.ActionEvent event) {
    }

}
