package PitStop.Model;

/**
 * Created by John on 2017-10-28.
 */
public class User {

    private String username, email, userType, password;
    private int id;

    public User(int id, String username, String password, String email, String userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getUserType() {
        return userType;
    }
}
