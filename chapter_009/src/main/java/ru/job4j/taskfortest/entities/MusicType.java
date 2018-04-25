package ru.job4j.taskfortest.entities;

import java.util.List;

/**
 * Class represents music type.
 *
 * @since 19/02/2018
 * @version 1
 */
public class MusicType {

    private int id;
    private String name;
    private List<User> users; //User : MusicType (M:M)

    public MusicType() {
    }

    public MusicType(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public MusicType(int id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
