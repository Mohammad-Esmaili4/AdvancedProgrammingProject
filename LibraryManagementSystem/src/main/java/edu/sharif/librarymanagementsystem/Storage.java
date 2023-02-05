package edu.sharif.librarymanagementsystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sharif.librarymanagementsystem.book.Book;
import edu.sharif.librarymanagementsystem.member.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static edu.sharif.librarymanagementsystem.librarian.BookMenuController.saveBookData;
import static edu.sharif.librarymanagementsystem.librarian.MemberMenuController.saveMemberData;

/**
 * This class holds the data of the hole program
 * All members and books are stored in arrays here
 * It also contains the currentUser which resembles the current user of the system
 * It also provides methods through which data sources are updated or overwritten or created
 * It reads the saved data from file when the program starts
 */
public class Storage {
    /**
     * An array that stores members of the system
     */
    public static ArrayList<Member> members = new ArrayList<>();
    /**
     * An array that stores books of the library
     */
    public static ArrayList<Book> books = new ArrayList<>();
    /**
     * This stores username and full name of members to make the search process easier
     */
    public static HashMap<String,String> memberInfo = new HashMap<>();
    /**
     * Shows the current user of the system
     * It only shows members
     * If the librarian logs in, it stays null
     */
    private static Member currentUser;

    /**
     * Gets current user of the system
     * @return current user of the system
     */
    public static Member getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user as the member passed into it
     * @param member Shows the current user that has loged in
     */
    public static void setCurrentUser(Member member) {
        currentUser = member;
    }

    /**
     * Finds a user with its username
     * @param username Shows the username supposed to be found
     * @return Member with the entered username
     * It returns null if such member does not exist
     */
    public static Member findUser(String username){
        for (Member member : members) {
            if(member.getUserName().equals(username)){
                return member;
            }
        }
        return null;
    }

    /**
     * It sets the current user as null
     * It is used when a member logs out or when a librarian logs in
     * It is also used when the program starts
     */
    public static void setCurrentUserNull(){
        currentUser = null;
    }

    /**
     * It reads member data source from file and add it to the program at the beginning of run
     * @throws IOException If address does not exist
     */
    public static void updateMembers() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("MemberDataSource.json"));
            String line;
            while ((line = reader.readLine())!=null){
                Gson gson = new GsonBuilder().create();
                Member member = gson.fromJson(line,Member.class);
                members.add(member);
                memberInfo.put(member.getUserName(),member.getPassword());
            }
            reader.close();
        }
        catch (IOException e) {
            createEmptyMemberFile();
        }
    }

    /**
     * It creates empty member data source if it does not exist in the path mentioned
     * @throws IOException
     */
    private static void createEmptyMemberFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("MemberDataSource.json"));
        writer.close();
        updateMembers();
    }

    /**
     * It reads book data source from file and add it to the program at the beginning of run
     * @throws IOException If address does not exist
     */
    public static void updateBooks() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("BookDataSource.json"));
            String line;
            while ((line = reader.readLine())!=null){
                Gson gson = new GsonBuilder().create();
                Book book = gson.fromJson(line,Book.class);
                books.add(book);
            }
            reader.close();
        }
        catch (IOException e) {
            createEmptyBookFile();
        }
    }
    /**
     * It creates empty member data source if it does not exist in the path mentioned
     * @throws IOException
     */
    private static void createEmptyBookFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("BookDataSource.json"));
        writer.close();
        updateBooks();
    }


    /**
     * It updates member data source when a change happened in the system
     */
    public static void restoreMemberData(){
        File file = new File("MemberDataSource.json");
        file.delete();
        for (Member member : members) {
            saveMemberData(member);
        }
    }
    /**
     * It updates book data source when a change happened in the system
     */
    public static void restoreBookData(){
        File file = new File("BookDataSource.json");
        file.delete();
        for (Book book : books) {
            saveBookData(book);
        }
    }

}
