package ru.job4j.taskfortest;

/**
 * Class user.
 *
 * @since 12/08/2017
 * @version 1
 */
public class User {

    /**
     * User's name.
     */
    private String name;

    /**
     * User's passport.
     */
    private int passport;

    /**
     * Constructor.
     * @param name - name
     * @param passport - pass
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for pass.
     * @return pass
     */
    public int getPassport() {
        return passport;
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

        if (passport != user.passport) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    /**
     * Hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + passport;
        return result;
    }
}
