package ru.job4j.simpleuserstorage;

import net.jcip.annotations.NotThreadSafe;

/**
 * Class plain simple user.
 *
 * @since 30/09/2017
 * @version 1
 */
@NotThreadSafe
public class User {

    /**
     * User's ID/
     */
    private final int id;

    /**
     * User's amount.
     */
    private int amount;

    /**
     * Constructor.
     *
     * @param id ID
     * @param amount amount
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getAmount();
        return result;
    }
}
