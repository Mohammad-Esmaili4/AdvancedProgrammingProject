package edu.sharif.librarymanagementsystem;


import edu.sharif.librarymanagementsystem.librarian.LibrarianController;
import edu.sharif.librarymanagementsystem.member.Member;
import edu.sharif.librarymanagementsystem.member.MemberController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * It is the main class of the program
 * It runs main menu and allows librarian and members to login
 */

public class Main extends Application {
    /**
     * Window that is opened
     * It is given by  the os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * A text field that gets the entered username
     */
    @FXML
    private TextField userName;
    /**
     * A text field that gets the entered password
     */
    @FXML
    private TextField password;

    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws IOException If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        URL url = getClass().getResource("main-menu.fxml");
        Scene scene = new Scene(FXMLLoader.load(url), 720, 480);
        stage.setTitle("Library Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method that runs the program
     * @param args Provided by os
     * @throws IOException when the addresses are null
     */
    public static void main(String[] args) throws IOException {
        Storage.updateMembers();
        Storage.updateBooks();
        Storage.setCurrentUserNull();
        launch();
    }

    /**
     * Closes the program
     * @param mouseEvent Shows that the button must be clicked
     */
    public void exitSystem(MouseEvent mouseEvent) {
        System.exit(0);
    }

    /**
     * It lets the admin(librarian) login the system
     * @param mouseEvent Shows that the button must be clicked
     * @throws Exception if stage is null
     */
    public void adminLogin(MouseEvent mouseEvent) throws Exception {
        if(userName.getText().equals("admin")&&password.getText().equals("admin")){
            userName.setText("");
            password.setText("");
            new LibrarianController().start(stage);
        }
    }
    /**
     * It lets the member login the system
     * @param mouseEvent Shows that the button must be clicked
     * @throws Exception if stage is null
     */
    public void memberLogin(MouseEvent mouseEvent) throws Exception {
        if(Storage.memberInfo.containsKey(userName.getText())){
            Member loginMember = Storage.findUser(userName.getText());
            if(loginMember.getPassword().equals(password.getText())){
                new MemberController().start(stage);
                Storage.setCurrentUser(loginMember);
                loginMember.setLoginState(true);
            }
        }
    }
}