package ru.job4j.crud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class represents simple city.
 *
 * @since 18/02/2018
 * @version 1
 */
@JsonAutoDetect
public class City {

    @JsonIgnore
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public City(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (getId() != city.getId()) return false;
        if (getName() != null ? !getName().equals(city.getName()) : city.getName() != null) return false;
        return getCountry() != null ? getCountry().equals(city.getCountry()) : city.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }
}
