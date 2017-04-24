package model;

public class User {

    private int user_id;
    private String user_username;
    private String user_displayname;
    private String user_password;
    private String user_tipe;

    public User() {
    }

    public User(String user_username, String user_displayname, String user_password, String user_tipe) {
        this.user_username = user_username;
        this.user_displayname = user_displayname;
        this.user_password = user_password;
        this.user_tipe = user_tipe;
    }

    
    public User(int user_id, String user_username, String user_displayname, String user_password, String user_tipe) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_displayname = user_displayname;
        this.user_password = user_password;
        this.user_tipe = user_tipe;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_displayname() {
        return user_displayname;
    }

    public void setUser_displayname(String user_displayname) {
        this.user_displayname = user_displayname;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_tipe() {
        return user_tipe;
    }

    public void setUser_tipe(String user_tipe) {
        this.user_tipe = user_tipe;
    }
    
}
