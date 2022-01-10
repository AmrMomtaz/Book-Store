package com.book_store.frontend;

import com.book_store.model.Book;
import com.book_store.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class modifyBookController {

    private User user;
    private Book book;
    public void setParameters(User user, Book book) {
        this.user = user;
        this.book = book;
    }
    @FXML
    public void saveChanges(ActionEvent event){

    }


}
