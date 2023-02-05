package edu.sharif.librarymanagementsystem.librarian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;


/**
 * This class is created to control the librarian book menu
 * It makes it possible to view the books of library and to remove them
 * It also provides the possibility to get to the previous window
 */

public class BookMenuController extends Application {
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
    private ListView<Book> listOfBooks;
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
        URL url = Main.class.getResource("librarian/book-menu.fxml");
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
     * It opens the add book menu by passing its address to changeScene method
     * @param mouseEvent Shows that we have to click the button
     * @throws IOException If address doesn't exist
     */
    public void openAddBook(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("librarian/add-book-menu.fxml");
        changeScene(url);
    }

    /**
     * It saves book data in the source by using Gson library
     * @param book It is the book that we want to save its info
     */
    public static void saveBookData(Book book){
        try {
            String bookData = gson.toJson(book);
            BufferedWriter writer = new BufferedWriter(new FileWriter("BookDataSource.json",true) );
            writer.write(bookData);
            writer.write(System.getProperty("line.separator"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It removes a book from data source
     * It actually removes data source completely and recreate it with new info
     * @param mouseEvent Shows that the button has to be clicked
     * @throws IOException If address doesn't exist
     */
    public void removeBook(MouseEvent mouseEvent) throws IOException {
        int selectedID = listOfBooks.getSelectionModel().getSelectedIndex();
        Book selectedBook = listOfBooks.getSelectionModel().getSelectedItem();
        Storage.books.remove(selectedBook);
        listOfBooks.getItems().remove(selectedID);
        Storage.restoreBookData();
    }

    /**
     * It changes the scene by passing librarian menu address to changeScene method
     * @param mouseEvent Shows that the button has to be clicked
     * @throws IOException If address doesn't exist
     */
    public void backToLibrarianMenu(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("librarian/librarian-menu.fxml");
        changeScene(url);
    }

    /**
     * This method is called when every object in the stage is defined to avoid nullPointerException
     */
    @FXML
    public void initialize() {
        for (Book book : Storage.books) {
            listOfBooks.getItems().add(book);
        }
    }
}
