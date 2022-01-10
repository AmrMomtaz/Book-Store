package com.book_store.frontend;

import com.book_store.model.Book;
import com.book_store.model.ShoppingCart;
import com.book_store.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class WelcomeScreenController{
    private final int pageSize = 10;
    @FXML
    private AnchorPane manager,bookInfoContainer;
    @FXML
    private TextField buyCount,searchField;
    @FXML
    private Label name,category,publicationYear,price,stockCount,pageNumber;
    @FXML
    private ComboBox searchByBox;
    @FXML
    private ListView<String> booksList;

    private boolean isFirstSelection = true;
    private String searchCriteria;
    private List<Book> books = new LinkedList<>();

    private User user;
    private Book book;

    public void setUser(User User){
        user = User;
    }
    private void fillSearchBox(){
        searchByBox.getItems().add("title");
        searchByBox.getItems().add("ISBN");
        searchByBox.getItems().add("publisher");
        searchByBox.getItems().add("publication year");
        searchByBox.getItems().add("category");

        searchCriteria = "title";
    }
    public void start() {
        //books.add(new Book("1","title","ana","2020",200,"history",10,10,null));
        fillSearchBox();
        if(user!=null){
            System.out.println(user.getType());
            if(user.getType().equals("customer")){
                manager.setOpacity(0);
                manager.setDisable(true);
            }
            bookInfoContainer.setOpacity(0);
            bookInfoContainer.setDisable(true);
        }
        booksList.setStyle("-fx-font-size: 1.3em; -fx-font-style: italic; -fx-font-weight: bold;");
        books = frontEndDAO.dao.listBooks(pageSize,Integer.parseInt(pageNumber.getText()));
        booksList.getItems().addAll(booksToArray());
        booksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                int index = booksList.getSelectionModel().getSelectedIndex();
                if(books.size()>index){
                    book = books.get(index);
                    name.setText(book.getTitle());
                    category.setText(book.getCategory());
                    publicationYear.setText(book.getPublication_year());
                    price.setText(Integer.toString(book.getSelling_price()));
                    stockCount.setText(Integer.toString(book.getThreshold()));
                    if(isFirstSelection){
                        isFirstSelection = false;
                        bookInfoContainer.setOpacity(1);
                        bookInfoContainer.setDisable(false);
                    }
                }
            }
        });
    }
    private String[] booksToArray(){
        String[] booksNames = new String[10];
        for (int i=0 ; i<books.size() && i<10 ; i++){
            booksNames[i] = books.get(i).getTitle();
        }
        for(int i=0; i<10 ; i++){
            if(booksNames[i] ==null)
                booksNames[i]= "";
        }
        return booksNames;
    }
    public void next(ActionEvent event) {
        if(books.size()>0){
            int page = Integer.parseInt(pageNumber.getText())+1;
            pageNumber.setText(Integer.toString(page));
            books = frontEndDAO.dao.listBooks(pageSize, page);
            booksList.getItems().remove(0,10);
            booksList.getItems().addAll(booksToArray());
        }
    }

    public void previous(ActionEvent event) {
        if(Integer.parseInt(pageNumber.getText())>1){
            int page = Integer.parseInt(pageNumber.getText())-1;
            pageNumber.setText(Integer.toString(page));
            books = frontEndDAO.dao.listBooks(pageSize, page);
            booksList.getItems().remove(0,10);
            booksList.getItems().addAll(booksToArray());
        }
    }

    public void search(ActionEvent event) {
        searchCriteria = searchByBox.getValue()!=null ? (String)searchByBox.getValue() : searchCriteria;
        System.out.println(searchCriteria);
        System.out.println(searchField.getText());
        // paging
        int page = Integer.parseInt(pageNumber.getText());
        pageNumber.setText(Integer.toString(page));
        switch (searchCriteria) {
            case "title":
                books = frontEndDAO.dao.searchBookByTitle(searchField.getText(), pageSize, page);
                break;
            case "ISBN":
                books = frontEndDAO.dao.searchBookByISBN(searchField.getText(), pageSize, page);
                break;
            case "publisher":
                books = frontEndDAO.dao.searchBookByPublisher(searchField.getText(), pageSize, page);
                break;
            case "publication year":
                books = frontEndDAO.dao.searchBookByPublication_year(searchField.getText(), pageSize, page);
                break;
            case "selling price":
                books = frontEndDAO.dao.searchBookByCategory(searchField.getText(), pageSize, page);
                break;
        }
        booksList.getItems().remove(0,10);
        booksList.getItems().addAll(booksToArray());
    }

    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addToCart(ActionEvent event) {
        if(!buyCount.getText().isEmpty() && buyCount.getText().matches("-?(0|[1-9]\\d*)")){
            ShoppingCart cartItem = new ShoppingCart(user.getID(),book.getISBN(),Integer.parseInt(buyCount.getText()));
            frontEndDAO.dao.addItemInShoppingCart(cartItem);
        }
    }

    public void reportAnalysis(ActionEvent event) {

    }

    public void addBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addBook.fxml"));
        Parent root = loader.load();
        addBookController addBookController = loader.getController();
        addBookController.setUser(user);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showUsers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/users.fxml"));
        Parent root = loader.load();
        usersController usersController = loader.getController();
        usersController.setUser(user);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void modifyBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/modifyBook.fxml"));
        Parent root = loader.load();
        modifyBookController modifyBookController = loader.getController();
        modifyBookController.setParameters(user,book);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void checkout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shoppingCart.fxml"));
        Parent root = loader.load();
        shoppingCartController shoppingCartController = loader.getController();
        shoppingCartController.setUser(user);
        shoppingCartController.start();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void confirmOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/confirmOrders.fxml"));
        Parent root = loader.load();
        confirmOrdersController confirmOrdersController = loader.getController();
        confirmOrdersController.setUser(user);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void updateUserInfo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateUserInfo.fxml"));
        Parent root = loader.load();
        updateUserInfoController updateUserInfoController = loader.getController();
        updateUserInfoController.setUser(user);
        updateUserInfoController.start();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
