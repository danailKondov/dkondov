package ru.job4j.autostorage.models;

/**
 * Created on 10.05.2018.
 */
public class Engine {

    private int id;
    private String engineName;

    public Engine(String engineName) {
        this.engineName = engineName;
    }

    public Engine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", engineName='" + engineName + '\'' +
                '}';
    }
}
