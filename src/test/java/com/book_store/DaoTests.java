package com.book_store;

import com.book_store.dao.DAO;
import com.book_store.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DaoTests {

    private DAO dao;

    @Autowired
    public DaoTests(DAO dao) {
        this.dao = dao;
    }

    @Test
    @DisplayName("Login functions test")
    void loginTests(){
        User test = new User("t","t","t","t",
                "t","t","t");
        Assertions.assertEquals(1,dao.createUser(test));
        Assertions.assertTrue(dao.checkEmailExists("t"));
        System.out.println(dao.login("t","t"));
        Assertions.assertEquals(1,dao.deleteUser(test.getID()));
    }

    @Test
    @DisplayName("Handling books")
    void booksTests(){
        Book test = new Book("t","t","PUBLISHER_TEST","t",
                100,"science",50,200, Arrays.asList("author1","author2","author3"));
        Assertions.assertEquals(1,dao.createPublisher(
                new Publisher("PUBLISHER_TEST","t","t")));
        Assertions.assertEquals(1,dao.createBook(test));
        System.out.println(dao.getBookByISBN("t"));
        test.setTitle("NEW TITLE");
        test.setAuthors(Arrays.asList("new author1","new author2"));
        Assertions.assertEquals(1,dao.updateBook("t",test));
        System.out.println(dao.getBookByISBN("t"));
        Assertions.assertEquals(1,dao.deleteBook(test.getISBN()));
        Assertions.assertEquals(1,dao.deletePublisher("PUBLISHER_TEST"));
    }

    @Test
    @DisplayName("Searching books")
    void booksSearchingTests() {
        Assertions.assertEquals(1,
                dao.createPublisher(new Publisher("publisherTest","t","t")));
        for (int i = 10; i < 20; i++) {
            Assertions.assertEquals(1,dao.createBook(
                    new Book(Integer.toString(i),"t","publisherTest","t",
                            100,"science",50,200,
                            Arrays.asList("author1","tt","ss"))));
        }
        List<Book> bookList = dao.searchBooksByAuthor("s",7,1);
        for (Book book : bookList)
            System.out.println(book);
        for (int i = 10; i < 20; i++)
            Assertions.assertEquals(1,dao.deleteBook(Integer.toString(i)));
        Assertions.assertEquals(1,dao.deletePublisher("publisherTest"));
    }

    @Test
    @DisplayName("Testing shopping cart and authentication")
    void shoppingCartTests(){
        CreditCard creditCard = new CreditCard("test","test");
        Assertions.assertEquals(1,dao.createCreditCard(creditCard));
        User user1 = new User("t","t","t","t","t","t","t");
        User user2 = new User("t","t","t","t","tt","t","t");
        User user3 = new User("t","t","t","t","ttt","t","t");
        Assertions.assertEquals(1,dao.createUser(user1));
        Assertions.assertEquals(1,dao.createUser(user2));
        Assertions.assertEquals(1,dao.createUser(user3));
        Assertions.assertEquals(1,dao.createPublisher(
                new Publisher("PUBLISHER_TEST","t","t")));
        Book book1 = new Book("t","t","PUBLISHER_TEST","t",
                100,"science",50,200, Arrays.asList("author1","author2","author3"));
        Book book2 = new Book("tt","t","PUBLISHER_TEST","t",
                100,"science",50,200, Arrays.asList("author1","author2","author3"));
        Book book3 = new Book("ttt","t","PUBLISHER_TEST","t",
                100,"science",50,200, Arrays.asList("author1","author2","author3"));
        Assertions.assertEquals(1,dao.createBook(book1));
        Assertions.assertEquals(1,dao.createBook(book2));
        Assertions.assertEquals(1,dao.createBook(book3));

        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user1.getID(),book1.getISBN(),10)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user1.getID(),book2.getISBN(),20)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user1.getID(),book3.getISBN(),30)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user2.getID(),book1.getISBN(),10)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user2.getID(),book2.getISBN(),20)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user3.getID(),book1.getISBN(),10)
        ));
        Assertions.assertEquals(1,dao.addItemInShoppingCart(new ShoppingCart(
                user3.getID(),book2.getISBN(),20)
        ));

        List<ShoppingCart> shoppingCartList = dao.listItemsInShoppingCart(user1.getID(),2,2);
        for(ShoppingCart item : shoppingCartList)
            System.out.println(item);

        Assertions.assertEquals(1,dao.confirmPurchase(creditCard,user1.getID()));


        Assertions.assertEquals(1,dao.deleteCartItem(user3.getID(),book1.getISBN()));
        Assertions.assertEquals(1,dao.deleteCartItem(user3.getID(),book2.getISBN()));
        Assertions.assertEquals(2,dao.userLogout(user2.getID()));

        Assertions.assertEquals(1,dao.deleteBook("t"));
        Assertions.assertEquals(1,dao.deleteBook("tt"));
        Assertions.assertEquals(1,dao.deletePublisher("PUBLISHER_TEST"));
        Assertions.assertEquals(1,dao.deleteUser(user1.getID()));
        Assertions.assertEquals(1,dao.deleteUser(user2.getID()));
        Assertions.assertEquals(1,dao.deleteUser(user3.getID()));
        Assertions.assertEquals(1,dao.deleteCreditCard("test"));

    }

    @Test
    @DisplayName("User update tests")
    void userUpdateTests(){
        User user1 = new User("t","t","t","t","t","t","t");
        User user2 = new User("t","t","t","t","tt","t","t");
        User user3 = new User("t","t","t","t","ttt","t","t");
        Assertions.assertEquals(1,dao.createUser(user1));
        Assertions.assertEquals(1,dao.createUser(user2));
        Assertions.assertEquals(1,dao.createUser(user3));

        List<User> customers = dao.listCustomers(2,2);
        for (User customer : customers)
            System.out.println(customer);

        user1.setUsername("New User Name");
        dao.updateUser(user1);
        dao.promoteUser(user1);

        Assertions.assertEquals(1,dao.deleteUser(user1.getID()));
        Assertions.assertEquals(1,dao.deleteUser(user2.getID()));
        Assertions.assertEquals(1,dao.deleteUser(user3.getID()));
    }
}
