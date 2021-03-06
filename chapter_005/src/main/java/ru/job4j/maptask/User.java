package ru.job4j.maptask;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class user.
 *
 * @since 28/08/2017
 * @version 1
 */
public class User {

    /**
     * User's name.
     */
    private String name;

    /**
     * Number of user's children.
     */
    private int children;

    /**
     * User's birthday.
     */
    private Calendar birthday;

    /**
     * Constructor.
     *
     * @param name user's name
     * @param children number of user's children
     * @param birthYear - year of birth
     * @param birthMonth - month of birth
     * @param birthDayOfMonth - day of birth
     */
    public User(String name, int children, int birthYear, int birthMonth, int birthDayOfMonth) {
        this.name = name;
        this.children = children;
        this.birthday = new GregorianCalendar(birthYear, birthMonth, birthDayOfMonth);
    }

    /**
     * Tests for equality.
     * @param obj to test
     * @return true if objects equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        User other = (User) obj;

        if (name != null? !name.equals(other.name) : other.name != null) return false;
        if (birthday != null? !birthday.equals(other.birthday) : other.birthday != null) return false;
        return children == other.children;
    }

    /**
     * Hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() + children * 31 + birthday.hashCode();
    }

    public static void main(String[] args) {
        User one = new User("Masha", 2, 1980, 9, 15);
        User two = new User("Masha", 2, 1980, 9, 15);

        Map<User, Object> map = new HashMap<>();
        map.put(one, new Object());
        map.put(two, new Object());

        System.out.println(map);
    }
}
