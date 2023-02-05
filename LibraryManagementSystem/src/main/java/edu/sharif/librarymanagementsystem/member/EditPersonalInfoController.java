package edu.sharif.librarymanagementsystem.member;

import edu.sharif.librarymanagementsystem.Main;
import edu.sharif.librarymanagementsystem.Storage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;

/**
 * This class controls Edit Personal Info Menu
 * It helps the member to edit his/her personal info
 * It is possible to get back to the previous window and save the given data
 */
public class EditPersonalInfoController extends Application {
    /**
     * Window that is opened
     * It is given by os at the start of the program
     * It is set to each class by passing it to the start method
     */
    private static Stage stage;
    /**
     * A label to send errors when changing info
     */
    @FXML
    private Label ErrorText;
    /**
     * A text field that gets new username
     */
    @FXML
    private TextField newUsername;
    /**
     * A text field that gets new password
     */
    @FXML
    private TextField newPassword;
    /**
     * A text field that gets new full name
     */
    @FXML
    private TextField newFullName;
    /**
     * A text field that gets new national code
     */
    @FXML
    private TextField newNationalCode;
    /**
     * A text field that gets new password repeat to check they are the same
     */
    @FXML
    private TextField passRepeat;
    /**
     * This method is defined in Application
     * Overriding this is mandatory for all classes that extend Application
     * @param stage Window that is opened
     * @throws Exception If address doesn't exist
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        URL url = Main.class.getResource("member/edit-personal-info-menu.fxml");
        Scene scene = new Scene(FXMLLoader.load(url), 720, 480);
        stage.setTitle("Library Management System");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is called when every object in the stage is defined to avoid nullPointerException
     */
    @FXML
    public void initialize(){
        Member member = Storage.getCurrentUser();
        newUsername.setText(member.getUserName());
        newFullName.setText(member.getFullName());
        newNationalCode.setText(String.valueOf(member.getNationalCode()));
    }

    /**
     * This method provides the ability to save the entered new info
     * It also gives error if the entered info is not proper
     * @param mouseEvent Shows that the button has to be clicked
     */
    public void changeInfo(MouseEvent mouseEvent) {
        String name = newFullName.getText();
        String username = newUsername.getText();
        String password = newPassword.getText();
        String passRep = passRepeat.getText();
        Integer newNC = Integer.parseInt(newNationalCode.getText());
        if(password.equals(passRep)){
            Member member = Storage.getCurrentUser();
            if(!Storage.memberInfo.containsKey(username)||member.getUserName().equals(username)){
                member.setUserName(username);
                member.setFullName(name);
                member.setPassWord(password);
                member.setNationalCode(newNC);
                Storage.restoreMemberData();
                newPassword.setText("");
                passRepeat.setText("");
                ErrorText.setText("Information changed successfully!");
            }else {
                ErrorText.setText("Username exists!");
            }
        }else{
           ErrorText.setText("Password is not repeated correctly!");
        }
    }

    /**
     * Helps the user to get back to the member controller view
     * @param mouseEvent Shows that the button has to be clicked
     * @throws Exception if stage is null
     */
    public void previousWindow(MouseEvent mouseEvent) throws Exception {
        new MemberController().start(stage);
    }
}
