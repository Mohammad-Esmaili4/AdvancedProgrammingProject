package edu.sharif.librarymanagementsystem.user;

/**
 * It is an abstract class that includes the general features and methods of any user of the system
 * It is a model of a real world user that is modeled by oop tools
 * To avoid complexity detailed features are not used in this modeling
 */
public abstract class User{
    /**
     * Shows username of a user
     */
    protected String userName;
    /**
     * Shows password of a user
     */
    protected String password;
    /**
     * Shows name of a user
     */
    protected String fullName;
    /**
     * Shows national code of a user
     */
    protected Integer nationalCode;
    /**
     * Shows login state of a user
     */
    protected boolean loginState;
    /**
     * Sets the name of a user
     * @param name is the given name to the user
     */
    public void setFullName(String name) {
        this.fullName = name;
    }

    /**
     * Gets the national code of a user
     * @return User's national code
     */
    public Integer getNationalCode() {
        return nationalCode;
    }
    /**
     * Sets the national code of a member
     * @param nationalCode is the given national code to the member
     */
    public void setNationalCode(Integer nationalCode) {
        this.nationalCode = nationalCode;
    }
    /**
     * Gets the password of the user
     * @return User's password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Gets the username of the user
     * @return User's username
     */
    public String getUserName() {
        return this.userName;
    }
    /**
     * Gets the full name of the user
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * Sets the user login state
     * @param loginState Shows that user has logged in as a boolean
     */
    public void setLoginState(boolean loginState){
        this.loginState = loginState;
    }
    /**
     * Sets the user username
     * @param userName Shows the new username that has to be set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Sets the user password
     * @param passWord Shows the new password that has to be set
     */
    public void setPassWord(String passWord){
        this.password = passWord;
    }
}

