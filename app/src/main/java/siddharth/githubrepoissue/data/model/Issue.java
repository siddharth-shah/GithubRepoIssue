package siddharth.githubrepoissue.data.model;

/**
 * Created by siddharth on 18/5/17.
 */

public class Issue {

    int id;
    String state;
    String title;
    String body;
    User user;
    int number;

    public Issue(int id, String state, String title, String body, User user, int number) {
        this.id = id;
        this.state = state;
        this.title = title;
        this.body = body;
        this.user = user;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
