package ru.job4j.conversion;

/**
 * Class user.
 *
 * @since 11/08/2017
 * @version 1
 */
public class User {
    /**
     * ID counter.
     */
    private static int userID = 0;

    /**
     * ID.
     */
    private int id = userID++;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's sity.
     */
    private String city;

    /**
     * Constructor with parameters.
     * @param name - user's name
     * @param city - user's sity
     */
    public User(String name, String city) {
        this.name = name;
        this.city = city;
    }

    /**
     * Getter for ID.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for name.
     * @return - user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for city.
     * @return user's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Equals.
     * @param o - object for comparison
     * @return - result of comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return city != null ? city.equals(user.city) : user.city == null;
    }

    /**
     * Hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
