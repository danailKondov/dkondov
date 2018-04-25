package ru.job4j.taskfortest.entities;

import java.util.List;

/**
 * Class represents role.
 *
 * @since 19/02/2018
 * @version 1
 */
public class Role {

    private int id;
    private String name;
    private List<User> users; // Role : User(1:M)

    public Role() {
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
