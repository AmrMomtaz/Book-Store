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

public class addBookController {
    public Label errorMessage;
    @FXML
    public TextField title, ispn,publisher,threshold;
    @FXML
    public TextArea authors;
    @FXML
    public DatePicker publicationYear;
    @FXML
    public TextField sellingPrice;
    @FXML
    public ChoiceBox category;
    @FXML
    public TextField copies;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }


    @FXML
    public void saveBook(ActionEvent event) throws IOException {
        if(invalidateFields()){
            errorMessage.setText("please fill all fields");
        }
        else{
            String[] authArray = authors.getText().split(",");
            List<String> authors = new LinkedList<>();
            for (int i = 0; i < authArray.length; i++) {
                authors.add(authArray[i]);
            }
            System.out.println(publicationYear.getValue().toString());
            System.out.println(category.getValue().toString());
            Book book = new Book(ispn.getText(),title.getText(),publisher.getText(),publicationYear.getValue().toString()
                    ,Integer.parseInt(sellingPrice.getText()),category.getValue().toString(),Integer.parseInt(threshold.getText()),
                    Integer.parseInt(copies.getText()),authors);
            frontEndDAO.dao.createBook(book);
            System.out.println(book);
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
                publicationYear.getValue()==null||
                sellingPrice.getText().isEmpty() ||
                !sellingPrice.getText().matches("-?(0|[1-9]\\d*)")||
                category.getValue()==null;
    }

}
