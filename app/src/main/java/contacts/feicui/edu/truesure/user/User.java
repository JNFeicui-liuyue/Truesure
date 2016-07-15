package contacts.feicui.edu.truesure.user;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyue on 2016/7/15.
 */
public class User {

//    "UserName":"qjd",
//    "Password":"654321"

    @SerializedName("UserName")
    private String username;

    @SerializedName("Password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
