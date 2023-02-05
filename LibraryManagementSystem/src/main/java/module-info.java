/**
 * Module for the hole program that lets javaFX and Gson have accessibility to classes
 */
module edu.sharif.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens edu.sharif.librarymanagementsystem to javafx.fxml, com.google.gson;
    exports edu.sharif.librarymanagementsystem;
    opens edu.sharif.librarymanagementsystem.user to javafx.fxml, com.google.gson;
    exports edu.sharif.librarymanagementsystem.user;
    opens edu.sharif.librarymanagementsystem.librarian to javafx.fxml, com.google.gson;
    exports edu.sharif.librarymanagementsystem.librarian;
    opens edu.sharif.librarymanagementsystem.member to javafx.fxml, com.google.gson;
    exports edu.sharif.librarymanagementsystem.member;
    opens edu.sharif.librarymanagementsystem.book to javafx.fxml, com.google.gson;
    exports edu.sharif.librarymanagementsystem.book;
}