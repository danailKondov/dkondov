package ru.job4j.taskfortest.entities;

/**
 * Class represents address.
 *
 * @since 19/02/2018
 * @version 1
 */
public class Address {

    private int id;
    private String address;

    public Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public Address(String address) {
        this.address = address;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
