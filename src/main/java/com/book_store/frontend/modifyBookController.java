package com.book_store.frontend;

import com.book_store.model.Book;
import com.book_store.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class modifyBookController {

    public Label errorMessage;
    @FXML
    public TextField title, ispn,publisher,threshold;
    @FXML
    public TextArea authors;
    @FXML
    public TextField publicationYear;
    @FXML
    public TextField sellingPrice;
    @FXML
    public ChoiceBox category;
    @FXML
    public TextField copies;
    private User user;
    private Book book;
    public void setParameters(User user, Book book) {
        this.user = user;
        this.book = book;
        category.getItems().add("Science");
        category.getItems().add("Art");
        category.getItems().add("Religion");
        category.getItems().add("History");
        category.getItems().add("Geography");
    }
    public void start(){
        title.setText(book.getTitle());
        copies.setText(Integer.toString(book.getCopies()));
        threshold.setText(Integer.toString(book.getThreshold()));
        ispn.setText(book.getISBN());
        publisher.setText(book.getPublisher());
        publicationYear.setText(book.getPublication_year());
        StringBuilder auth = new StringBuilder();
        List<String> authList = book.getAuthors();
        for (int i = 0; i < authList.size(); i++) {
            auth.append(authList.get(i));
            auth.append(" ,");
        }
        authors.setText(auth.toString());
        sellingPrice.setText(Integer.toString(book.getSelling_price()));
    }
    @FXML
    public void saveChanges(ActionEvent event) throws IOException {
        if(invalidateFields()){
            errorMessage.setText("please fill all fields");
        }
        else{
            String[] authArray = authors.getText().split(",");
            List<String> authors = new LinkedList<>();
            for (int i = 0; i < authArray.length; i++) {
                authors.add(authArray[i]);
            }
            System.out.println(publicationYear.getText());
            System.out.println(category.getValue().toString());
            Book changedBook = new Book(ispn.getText(),title.getText(),publisher.getText(),publicationYear.getText()
                    ,Integer.parseInt(sellingPrice.getText()),category.getValue().toString(),Integer.parseInt(threshold.getText()),
                    Integer.parseInt(copies.getText()),authors);
            frontEndDAO.dao.updateBook(book.getISBN(),changedBook);
            System.out.println(changedBook);
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
    private boolean invalidateFields(){
        return  title.getText().isEmpty() ||
                copies.getText().isEmpty()||
                !copies.getText().matches("-?(0|[1-9]\\d*)")||
                threshold.getText().isEmpty() ||
                !threshold.getText().matches("-?(0|[1-9]\\d*)")||
                ispn.getText().isEmpty() ||
                publisher.getText().isEmpty() ||
                authors.getText().isEmpty() ||
                publicationYear.getText().isEmpty()||
                sellingPrice.getText().isEmpty() ||
                !sellingPrice.getText().matches("-?(0|[1-9]\\d*)")||
                category.getValue()==null;
    }


}
