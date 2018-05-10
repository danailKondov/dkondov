package ru.job4j.autostorage.models;

/**
 * Created on 10.05.2018.
 */
public class Gearbox {

    private int id;
    private String gearboxName;

    public Gearbox(String gearboxName) {
        this.gearboxName = gearboxName;
    }

    public Gearbox() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGearboxName() {
        return gearboxName;
    }

    public void setGearboxName(String gearboxName) {
        this.gearboxName = gearboxName;
    }

    @Override
    public String toString() {
        return "Gearbox{" +
                "id=" + id +
                ", gearboxName='" + gearboxName + '\'' +
                '}';
    }
}
