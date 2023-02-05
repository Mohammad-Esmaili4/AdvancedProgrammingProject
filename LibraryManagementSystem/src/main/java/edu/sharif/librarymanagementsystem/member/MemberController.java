package edu.sharif.librarymanagementsystem.member;


import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * It is the main class that controls member accessibility to the system
 * It is possible to logout and return to the main menu
 */

public class MemberController extends Application {
    /**
     * Window that is opened
     * It is given by the os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws Exception If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL memberUrl = Main.class.getResource("member/member-menu.fxml");
        stage.setResizable(false);
        Scene scene = new Scene(FXMLLoader.load(memberUrl));
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
     * Provides the possibility to logout
     * @param mouseEvent Shows that the button has to be clicked
     * @throws IOException if address passed to changeScene is null
     */
    public void logOut(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("main-menu.fxml");
        changeScene(url);
        Member member = Storage.getCurrentUser();
        member.setLoginState(false);
        Storage.setCurrentUserNull();
    }

    /**
     * It makes it possible to go to edit page of a member
     * It uses the Start method of an object from EditPersonalInfoController class
     * @param mouseEvent Shows that the button has to be clicked
     * @throws Exception if stage is null
     */
    public void openEditWindow(MouseEvent mouseEvent) throws Exception {
        new EditPersonalInfoController().start(stage);
    }

    /**
     * It makes it possible to go to mybooks of a member
     * It uses the Start method of an object from MyBooksController class
     * @param mouseEvent Shows that the button has to be clicked
     * @throws Exception if stage is null
     */
    public void openMyBooks(MouseEvent mouseEvent) throws Exception {
        new MyBooksMenuController().start(stage);
    }

    /**
     * It makes it possible to go to BorrowBook page of a member
     * It uses the Start method of an object from BorrowMenuController class
     * @param mouseEvent Shows that the button has to be clicked
     * @throws Exception if stage is null
     */
    public void openBorrowBook(MouseEvent mouseEvent) throws Exception {
       new BorrowMenuController().start(stage);
    }


}
