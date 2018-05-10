package ru.job4j.autostorage.models;

/**
 * Created on 10.05.2018.
 */
public class Transmission {

    private int id;
    private String transmissionName;

    public Transmission(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    public Transmission() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransmissionName() {
        return transmissionName;
    }

    public void setTransmissionName(String transmissionName) {
        this.transmissionName = transmissionName;
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", transmissionName='" + transmissionName + '\'' +
                '}';
    }
}
