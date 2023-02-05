package edu.sharif.librarymanagementsystem.member;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import edu.sharif.librarymanagementsystem.book.Book;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

import static edu.sharif.librarymanagementsystem.Storage.books;

/**
 * This class controls the Borrow Menu
 * It makes it possible for a member to see and borrow books
 * It is also possible to get back to previous window
 */

public class BorrowMenuController extends Application {
    /**
     * Window that is opened
     * It is given by os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * It's a label to send message when borrowing a book
     */
    @FXML
    private Label borrowBookMessage;
    /**
     * It is a javaFX special object
     * It makes it possible to view list of the books
     */
    @FXML
    private ListView<Book> listOfBooksToBorrow;
    /**
     * Gson library from Google
     * It makes it possible to write data in the disk or read data from it
     */
    private static Gson gson = new GsonBuilder().create();
    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws Exception If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL url = Main.class.getResource("member/borrow-menu.fxml");
        Scene scene = new Scene(FXMLLoader.load(url), 720, 480);
        stage.setTitle("Library Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * It changes the Scene and gets back to the member control menu
     * It works by calling the start method of member of MemberController class
     * @param mouseEvent Shows that it requires the button to be clicked
     * @throws Exception When stage is null
     */
    public void previousWindow(MouseEvent mouseEvent) throws Exception {
       new MemberController().start(stage);
    }

    /**
     * It provides the possibility for a member to borrow a selected book
     * It also saves the data in the source
     * @param mouseEvent Shows that it requires the button to be clicked
     */
    public void borrowABook(MouseEvent mouseEvent) {
        Book selectedBook = listOfBooksToBorrow.getSelectionModel().getSelectedItem();
        if(selectedBook!=null&&!selectedBook.isBorrowed()){
            Member member= Storage.getCurrentUser();
            member.addToListOfBooks(selectedBook);
            selectedBook.setBorrowState(true);
            Storage.restoreMemberData();
            Storage.restoreBookData();
            borrowBookMessage.setText("Borrowed successfully!");
        }else{
            borrowBookMessage.setText("The book is borrowed now!");
        }
    }
    /**
     * This method is called when every object in the stage is defined to avoid nullPointerException
     */
    @FXML
    public void initialize(){
        for (Book book : books) {
            listOfBooksToBorrow.getItems().add(book);
        }
    }

}
