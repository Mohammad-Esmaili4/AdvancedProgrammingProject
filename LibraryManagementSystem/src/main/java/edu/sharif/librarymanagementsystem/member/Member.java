package edu.sharif.librarymanagementsystem.member;

import edu.sharif.librarymanagementsystem.book.Book;
import edu.sharif.librarymanagementsystem.user.User;

import java.util.ArrayList;


/**
 * Member class represents Members of the library
 * In fact Members are defined as a class to model the real world Members with oop design model
 * As Members in the real world they have a name, a username and a national code
 * To avoid complexity other features of a real member are not used in our modeling
 * The members also have a state that shows if they are logged in the current situation or not
 * Note: It is a subclass of User class
 */

public class Member extends User {
    /**
     * It is list of the books that a member is borrowing
     */
    private ArrayList<Book> listOfBooks;

    /**
     * Constructor of the Member class that makes a member
     * @param fullName Shows Entered full name
     * @param username Shows Entered username
     * @param nationalCode Shows Entered national code
     * Other parameters are not passed and assigned automatically
     */
    public Member(String fullName, String username, Integer nationalCode){
        this.fullName = fullName;
        this.userName = username;
        this.nationalCode = nationalCode;
        this.password = "00000000";
        this.loginState = false;
        listOfBooks = new ArrayList<>();
    }
    /**
     * It is overridden to show books in the listviews of some methods
     * @return Member's Username + Member's full name
     */
    @Override
    public String toString(){
        return "Username: "+this.userName+"     Full Name: "+this.fullName+"       National Code: "+ this.nationalCode;
    }

    /**
     * It provides the accessibility to the private list of books
     * @return Member's list of borrowing books
     */
    public ArrayList<Book> getListOfBooks() {
        return listOfBooks;
    }

    /**
     * It provides the possibility to add a book to the list of books of member
     * In fact it is necessary to provide borrowing a book possibility
     * @param book The book that member wants to borrow
     */
    public void addToListOfBooks(Book book){
        this.listOfBooks.add(book);
    }
    /**
     * It provides the possibility to remove a book to the list of books of member
     * In fact it is necessary to provide returning a book possibility
     * @param book The book that member wants to return
     */
    public void removeFromBookList(Book book){
        if(this.listOfBooks.contains(book)){
            this.listOfBooks.remove(book);
        }
    }

}
