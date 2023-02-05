package edu.sharif.librarymanagementsystem.librarian;
import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import edu.sharif.librarymanagementsystem.book.Book;
import edu.sharif.librarymanagementsystem.member.Member;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.URL;

import static edu.sharif.librarymanagementsystem.Storage.*;


/**
 * This class is the main librarian controller that controls many actions
 * It makes it possible to open the librarian menu
 */


public class LibrarianController extends Application {
    /**
     * Window that is opened
     * It is given by os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * It's a label to show a message when librarian adds a member
     */
    @FXML
    private Label addMemberMessage;
    /**
     * It's a label to show a message when librarian adds a book
     */
    @FXML
    private Label addBookMessage;
    /**
     * A text field to get national code of member as input
     */
    @FXML
    private TextField nationalCodeOfNewMember;
    /**
     * A text field to get username of member as input
     */
    @FXML
    private TextField usernameOfNewMember;
    /**
     * A text field to get full name of member as input
     */
    @FXML
    private TextField fullNameOfNewMember;
    /**
     *  A text field to get author of a book as input
     */
    @FXML
    private TextField authorOfNewBook;
    /**
     * A text field to get genre of a book as input
     */
    @FXML
    private TextField genreOfNewBook;
    /**
     * A text field to get name of a book as input
     */
    @FXML
    private TextField nameOfNewBook;
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
        URL librarianMenu = Main.class.getResource("librarian/librarian-menu.fxml");
        stage.setResizable(false);
        Scene scene = new Scene(FXMLLoader.load(librarianMenu));
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
     * It saves member data in the source by using Gson library
     * @param member It is the member that we want to save its info
     */

    public static void saveMemberData(Member member){
        try {
            String bookData = gson.toJson(member);
            BufferedWriter writer = new BufferedWriter(new FileWriter("MemberDataSource.json",true) );
            writer.write(bookData);
            writer.write(System.getProperty("line.separator"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * It opens member menu by calling start method in the MemberMenuController
     * @param mouseEvent Shows that it requires clicking the button
     * @throws Exception If stage is null
     */
    public void openMemberMenu(MouseEvent mouseEvent) throws Exception {
        new MemberMenuController().start(stage);
    }

    /**
     * It helps a librarian to logout and gets to the main menu
     * It works by calling changeScene method and passing main menu address to it
     * @param mouseEvent Shows that it requires clicking the button
     * @throws IOException if the address does not exist
     */

    public void logOut(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("main-menu.fxml");
        changeScene(url);
        Storage.setCurrentUserNull();
    }

    /**
     * It opens book menu by calling start method in the BookMenuController
     * @param mouseEvent Shows that it requires clicking the button
     * @throws Exception If stage is null
     */
    public void openBookMenu(MouseEvent mouseEvent) throws Exception {
        new BookMenuController().start(stage);
    }

    /**
     * It gets back to member menu by calling changeScene method
     * @param mouseEvent Shows that it requires clicking the button
     * @throws IOException if the address does not exist
     */
    public void backToMemberMenu(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("librarian/members-of-library-menu.fxml");
        changeScene(url);
    }

    /**
     * It adds a member to the system
     * It also checks if the username already exists in the database
     * It works by calling the constructor of the Member class
     * @param mouseEvent Shows that it requires clicking the button
     */
    public void addMember(MouseEvent mouseEvent) {
        String username = usernameOfNewMember.getText();
        String fullName = fullNameOfNewMember.getText();
        if(!memberInfo.containsKey(username)){
            String nc = nationalCodeOfNewMember.getText();
            Integer nationalCode = Integer.parseInt(nc);
            usernameOfNewMember.setText("");
            fullNameOfNewMember.setText("");
            nationalCodeOfNewMember.setText("");
            Member member = new Member(fullName,username,nationalCode);
            saveMemberData(member);
            members.add(member);
            memberInfo.put(member.getUserName(),member.getPassword());
            addMemberMessage.setText("Member added successfully!");
        }else {
            addMemberMessage.setText("Username exists!");
            fullNameOfNewMember.setText("");
            nationalCodeOfNewMember.setText("");
        }
    }
    /**
     * It gets back book menu by calling start method in the BookMenuController
     * @param mouseEvent Shows that it requires clicking the button
     * @throws IOException If stage is null
     */
    public void backToBookMenu(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("librarian/book-menu.fxml");
        changeScene(url);
    }
    /**
     * It adds a book to the system
     * It also checks if the name already exists in the database
     * It works by calling the constructor of the Book class
     * @param mouseEvent Shows that it requires clicking the button
     */
    public void addBook(MouseEvent mouseEvent) {
        String name = nameOfNewBook.getText();
        String genre = genreOfNewBook.getText();
        String author = authorOfNewBook.getText();
        Integer flag = 1;
        for (Book book : books) {
            if(book.getName().equals(name)){
                flag = 0;
            }
        }
        if(flag==1){
            Book book = new Book(name,author,genre);
            saveBookData(book);
            nameOfNewBook.setText("");
            genreOfNewBook.setText("");
            authorOfNewBook.setText("");
            books.add(book);
            addBookMessage.setText("Book added successfully!");
        }
        else {
            addBookMessage.setText("Book exists!");
            genreOfNewBook.setText("");
            authorOfNewBook.setText("");
        }
    }

}
