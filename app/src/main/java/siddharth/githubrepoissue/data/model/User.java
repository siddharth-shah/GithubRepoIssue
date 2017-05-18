package siddharth.githubrepoissue.data.model;

/**
 * Created by siddharth on 18/5/17.
 */

public class User {

    int id;
    String login;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
