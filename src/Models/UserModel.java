package Models;

/** -------------------------------------------------------------------------------------------------------------------------- */

/** This class is for constructing the user model */
public class UserModel {

    /** Class Variables */
    private int userId;
    private String username;
    private String password;
    private boolean inSession;

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /** User Model Constructor */
    public UserModel(
            int userId,
            String username,
            String password
    ){
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /** User Model Constructor */
    public UserModel(
            int userId,
            String username
    ){
        this.userId = userId;
        this.username = username;
    }

    /** User Model Constructor */
    public UserModel(
            String username
    ){
        this.username = username;
    }

    /** User Model Constructor */
    public UserModel(
            int userId,
            String username,
            String password,
            boolean inSession
    ){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.inSession = inSession;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /** Getter and Setter Methods */

    /**
     * @return User Id
     **/
    public int getUserId() {
        return userId;
    }
    /**
     * @param userId User Id
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return Username of logged in user
     * */
    public String getUsername() {
        return username;
    }
    /**
     * @param username Username of logged in user
     * */
    public void setUsername(String username) {
        this.username = username;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */

    /**
     * @return true, if user is currently logged into application
     * */
    public boolean isInSession() {
        return inSession;
    }
    /**
     * @param inSession returns true, if user is currently logged into application
     * */
    public void setInSession(boolean inSession) {
        this.inSession = inSession;
    }

    /** --------------------------------------------------------------------------------------------------------------------------- */
    /**
     * returns concatenated string of user Id and username
     * */
    @Override
    public String toString(){
        return Integer.toString(userId) + " " + username;
    }
}
