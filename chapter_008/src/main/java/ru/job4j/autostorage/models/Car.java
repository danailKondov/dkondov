package ru.job4j.autostorage.models;

/**
 * Created on 10.05.2018.
 */
public class Car {

    private int id;
    private String carName;
    private Gearbox gearbox;
    private Transmission transmission;
    private Engine engine;

    public Car(String carName, Gearbox gearbox, Transmission transmission, Engine engine) {
        this.carName = carName;
        this.gearbox = gearbox;
        this.transmission = transmission;
        this.engine = engine;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", gearbox=" + gearbox +
                ", transmission=" + transmission +
                ", engine=" + engine +
                '}';
    }
}
