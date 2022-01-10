package com.book_store.frontend;

import com.book_store.model.CreditCard;
import com.book_store.model.ShoppingCart;
import com.book_store.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class shoppingCartController {
    @FXML
    public ListView booksList;
    @FXML
    public Label pageNumber,ISBN,price,purchaseCount,errorMessage;
    @FXML
    public TextField creditCardNumber, expireDate;
    @FXML
    AnchorPane bookInfoContainer;
    private final int pageSize =10;
    private ShoppingCart cartItem;
    private List<ShoppingCart> shoppingcart;
    private User user;
    private boolean isFirstSelection = true;
    public void setUser(User user) {
        this.user = user;
    }
    public void start() {
        bookInfoContainer.setOpacity(0);
        bookInfoContainer.setDisable(true);

        booksList.setStyle("-fx-font-size: 1.3em; -fx-font-style: italic; -fx-font-weight: bold;");
        shoppingcart = frontEndDAO.dao.listItemsInShoppingCart(user.getID(),pageSize,Integer.parseInt(pageNumber.getText()));
        booksList.getItems().addAll(ShoppingCartToArray());
        booksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index = booksList.getSelectionModel().getSelectedIndex();
                if(shoppingcart.size()>index){
                    cartItem = shoppingcart.get(index);
                    ISBN.setText(cartItem.getISBN());
                    price.setText(Integer.toString(cartItem.getPrice()));
                    purchaseCount.setText(Integer.toString(cartItem.getCount()));
                    if(isFirstSelection){
                        isFirstSelection = false;
                        bookInfoContainer.setOpacity(1);
                        bookInfoContainer.setDisable(false);
                    }
                }
            }
        });
    }

    private String[] ShoppingCartToArray() {
        String[] booksNames = new String[10];
        for (int i=0 ; i<shoppingcart.size() && i<10 ; i++){
            booksNames[i] = shoppingcart.get(i).getISBN();
        }
        for(int i=0; i<10 ; i++){
            if(booksNames[i] ==null)
                booksNames[i]= "";
        }
        return booksNames;
    }

    public void next(javafx.event.ActionEvent event) {
        if(shoppingcart.size()>0){
            int page = Integer.parseInt(pageNumber.getText())+1;
            pageNumber.setText(Integer.toString(page));
            shoppingcart = frontEndDAO.dao.listItemsInShoppingCart(user.getID(),pageSize, page);
            booksList.getItems().remove(0,10);
            booksList.getItems().addAll(ShoppingCartToArray());
        }
    }

    public void previous(javafx.event.ActionEvent event) {
        if(Integer.parseInt(pageNumber.getText())>1){
            int page = Integer.parseInt(pageNumber.getText())-1;
            pageNumber.setText(Integer.toString(page));
            shoppingcart = frontEndDAO.dao.listItemsInShoppingCart(user.getID(), pageSize, page);
            booksList.getItems().remove(0,10);
            booksList.getItems().addAll(ShoppingCartToArray());
        }
    }

    public void logout(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void removeBook(javafx.event.ActionEvent event) {
        //if(cartItem!=null)
            //frontEndDAO.dao.deleteCartItem(cartItem);
    }

    public void homePage(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/welcome.fxml"));
        Parent root = loader.load();
        WelcomeScreenController welcomeController = loader.getController();
        welcomeController.setUser(user);
        welcomeController.start();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void checkOut(javafx.event.ActionEvent event) {
        if(invalidCreditCardValidator()){
            errorMessage.setText("please fill credit card fields");
        }
        else{
            CreditCard creditCard = new CreditCard(creditCardNumber.getText(),expireDate.getText());
            int transaction = frontEndDAO.dao.confirmPurchase(creditCard,user.getID());
            if(transaction<0){
                errorMessage.setText("transaction failed");
            }
            else{
                errorMessage.setText("transaction completed successfully");
            }
        }
    }
    private boolean invalidCreditCardValidator(){
        return creditCardNumber.getText().isEmpty() ||
                expireDate.getText().isEmpty();
    }


}
