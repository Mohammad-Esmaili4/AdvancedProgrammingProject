package edu.sharif.librarymanagementsystem.book;

/**
 * Book class represents books of the library
 * In fact books are defined as a class to model the real world books with oop design model
 * As books in the real world they have a name, an author and a genre
 * To avoid complexity other features of a real book are not used in our modeling
 * The books also have a state that shows if they are borrowed in the current situation or not
 */
public class Book {
    /**
     * Shows name of a book
     */
    private String name;
    /**
     * Shows author of the book
     */
    private String nameOfAuthor;
    /**
     * Shows if the books is borrowed
     */
    private boolean isBorrowed;
    /**
     * Shows genre of the book
     */
    private String genre;

    /**
     * Constructor of the Book class which creates a book if parameters are passed
     * @param name Name of the book
     * @param nameOfAuthor Author of the book
     * @param genre Genre of the book
     */
    public Book(String name,String nameOfAuthor,String genre){
        this.name = name;
        this.nameOfAuthor = nameOfAuthor;
        this.genre = genre;
        this.isBorrowed = false;
    }

    /**
     * It is overridden to show books in the listviews of some methods
     * @return Book's name + Book's Author + Book's genre
     */
    @Override
    public String toString(){
        return "Name: "+this.name +"    Author: "+this.nameOfAuthor+ "  Genre: "+this.genre;
    }

    /**
     *
     * @return Book's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets the name of a book
     * @param name is the given name to the book
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the borrow state of the book as a boolean
     */
    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    /**
     * Sets the borrow state of the book
     * @param borrowState The borrow state of the book
     */
    public void setBorrowState(boolean borrowState) {
        this.isBorrowed = borrowState;
    }

}
