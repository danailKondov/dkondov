package ru.job4j.taskfortest;

/**
 * Class user's account.
 *
 * @since 12/08/2017
 * @version 1
 */
public class Account {

    /**
     * User's money volume.
     */
    private double value;

    /**
     * Account requisites.
     */
    private int requisites;

    /**
     * Constructor.
     * @param value - user's money volume
     * @param requisites - account requisites
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Getter for value.
     * @return user's money volume
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for value.
     * @param value new amount of user's money.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Getter for user's account requisites.
     * @return requisites
     */
    public int getRequisites() {
        return requisites;
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

        Account account = (Account) o;

        if (value != account.value) return false;
        return requisites == account.requisites;
    }

    /**
     * Hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = (int) value;
        result = 31 * result + requisites;
        return result;
    }

    /**
     * String representation of object.
     * @return string
     */
    @Override
    public String toString() {
        return "requisite: " + requisites + "; amount: " + value;
    }
}
