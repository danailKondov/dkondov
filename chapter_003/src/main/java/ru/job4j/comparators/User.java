package ru.job4j.comparators;

/**
 * Class simple user.
 *
 * @since 12/08/2017
 * @version 1
 */
public class User implements Comparable<User>{
    private String name;
    private int age;

    /**
     * Constructor.
     * @param name name
     * @param age age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for age.
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * For making users comparable.
     * @param o - other user
     * @return - result of comparison
     */
    @Override
    public int compareTo(User o) {
        return Integer.compare(age, o.getAge());
    }

    /**
     * String representation of object.
     * @return age of user
     */
    @Override
    public String toString() {
        return name + "-" + age;
    }
}
