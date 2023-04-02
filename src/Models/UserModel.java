package Models;

public class UserModel {
    private int userId;
    private String username;
    private String password;
    private boolean inSession;

    public UserModel(
            int userId,
            String username,
            String password
    ){
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UserModel(
            int userId,
            String username
    ){
        this.userId = userId;
        this.username = username;
    }

    public UserModel(
            String username
    ){
        this.username = username;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return Integer.toString(userId) + " " + username;
    }
}
