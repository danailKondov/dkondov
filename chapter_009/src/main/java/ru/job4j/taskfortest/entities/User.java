package ru.job4j.taskfortest.entities;

import java.util.List;

/**
 * Class represents user.
 *
 * @since 19/02/2018
 * @version 1
 */
public class User {

    private int id;
    private String name;
    private Role role; //Role : User(1:M)
    private Address address; //User : Address(1:1)
    private List<MusicType> music; //User : MusicType (M:M)

    public User(int id, String name, Role role, Address address, List<MusicType> music) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.address = address;
        this.music = music;
    }

    public User(String name, Role role, Address address, List<MusicType> music) {
        this.name = name;
        this.role = role;
        this.address = address;
        this.music = music;
    }

    public User() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MusicType> getMusic() {
        return music;
    }

    public void setMusic(List<MusicType> music) {
        this.music = music;
    }
}
