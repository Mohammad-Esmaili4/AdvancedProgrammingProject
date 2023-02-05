package edu.sharif.librarymanagementsystem.member;

import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import edu.sharif.librarymanagementsystem.book.Book;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * This class controls the MyBooksMenu of a member
 * It makes it possible to see the list of borrowed books and return them if the member wants
 */

public class MyBooksMenuController extends Application {
    /**
     * Window that is opened
     * It is given by  the os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * It is a javaFX special object
     * It makes it possible to view list of the books
     */
    @FXML
    private ListView<Book> listOfMyBooks;

    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws Exception If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL url = Main.class.getResource("member/my-books-menu.fxml");
        Scene scene = new Scene(FXMLLoader.load(url), 720, 480);
        stage.setTitle("Library Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * It changes the scene of the present window
     * Note: It does not create any Stage object
     * It just changes the scene of that
     * @param url Address of the new scene
     * @throws IOException If file doesn't exist
     */
    public void changeScene(URL url) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(url));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * It provides the possibility to return a book for a member
     * @param mouseEvent Shows that we have to click the button
     */
    public void returnABook(MouseEvent mouseEvent) {
        Book selectedBook = listOfMyBooks.getSelectionModel().getSelectedItem();
        if(selectedBook!=null){
            selectedBook.setBorrowState(false);
            Member member = Storage.getCurrentUser();
            member.removeFromBookList(selectedBook);
            Storage.restoreMemberData();
            Storage.restoreBookData();
            listOfMyBooks.getItems().remove(selectedBook);
        }
    }

    /**
     * It makes it possible to get back to member-menu page
     * It works by passing memberMenu page address to the changeScene method
     * @param mouseEvent Shows that we have to click the button
     * @throws IOException if address does not exist
     */
    public void previousWindow(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("member/member-menu.fxml");
        changeScene(url);
    }
    /**
     * This method is called when every object in the stage is defined to avoid nullPointerException
     */
    @FXML
    public void initialize() {
        Member member = Storage.getCurrentUser();
        for (Book book : member.getListOfBooks()) {
            listOfMyBooks.getItems().add(book);
        }
    }
}
