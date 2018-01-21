package ru.job4j.crud.model;

import java.sql.Timestamp;

/**
 * Class represents simple user.
 *
 * @since 17/01/2018
 * @version 1
 */

public class User {

    private int userID; // is created by DB
    private String name;
    private String login;
    private String email;
    private final Timestamp createDate;

    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        createDate = new Timestamp(System.currentTimeMillis());
    }

    public User(int userID, String name, String login, String email, Timestamp createDate) {
        this.userID = userID;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (getUserID() != user.getUserID()) return false;
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) return false;
        if (getLogin() != null ? !getLogin().equals(user.getLogin()) : user.getLogin() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        return getCreateDate() != null ? getCreateDate().equals(user.getCreateDate()) : user.getCreateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
