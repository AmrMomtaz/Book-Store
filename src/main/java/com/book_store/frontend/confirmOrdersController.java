package com.book_store.frontend;

import com.book_store.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class confirmOrdersController {
    public ListView orderList;
    public Label pageNumber;
    public AnchorPane orderContainer;
    public Label name;
    public Label category;
    public Label publicationYear;
    public Label price;
    public Label purchaseCount;
    private User user;


    public void setUser(User user) {
        this.user = user;
    }
    public void start(){

    }
    public void next(ActionEvent event) {
    }

    public void previous(ActionEvent event) {
    }

    public void logout(ActionEvent event) {
    }

    public void confirmOrder(ActionEvent event) {
    }

    public void homePage(ActionEvent event) {
    }


}
