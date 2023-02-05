package edu.sharif.librarymanagementsystem.librarian;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import edu.sharif.librarymanagementsystem.book.Book;
import edu.sharif.librarymanagementsystem.member.Member;
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
 * It controls member menu
 * By this we can open add member menu
 * We can delete a member
 * It also shows all members in the system to the librarian
 */

public class MemberMenuController extends Application {
    /**
     * Window that is opened
     * It is given by the os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * It is a javaFX special object
     * It makes it possible to view list of the members
     */
    @FXML
    private ListView<Member> listOfMembers;
    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws Exception If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL url = Main.class.getResource("librarian/members-of-library-menu.fxml");
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
     * It opens the add member menu by passing its address to changeScene method
     * @param mouseEvent Shows that we have to click the button
     * @throws IOException If address doesn't exist
     */
    public void openAddMember(MouseEvent mouseEvent) throws IOException {
        URL url = Main.class.getResource("librarian/add-member-menu.fxml");
        changeScene(url);
    }
    /**
     * It removes a member from data source
     * It actually removes data source completely and recreate it with new info
     * @param mouseEvent Shows that the button has to be clicked
     * @throws IOException If address doesn't exist
     */
    public void removeMember(MouseEvent mouseEvent) throws IOException {
        int selectedID = listOfMembers.getSelectionModel().getSelectedIndex();
        Member selectedUser =  listOfMembers.getSelectionModel().getSelectedItem();
        String username = selectedUser.getUserName();
        for (Book book : selectedUser.getListOfBooks()) {
            book.setBorrowState(false);
        }
        Storage.memberInfo.remove(username);
        Storage.members.remove(selectedUser);
        Storage.restoreMemberData();
        listOfMembers.getItems().remove(selectedID);
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
        for (Member member : Storage.members) {
            listOfMembers.getItems().add(member);
        }
    }

    /**
     * It saves member data in the source by using Gson library
     * @param member It is the member that we want to save its info
     */
    public static void saveMemberData(Member member){
        Gson gson = new GsonBuilder().create();
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
}
