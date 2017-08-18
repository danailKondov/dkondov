package ru.job4j.genericsimplearray;

/**
 * Class User.
 *
 * @since 18/08/2017
 * @version 1
 */
public class User extends Base {

    /**
     * User's name.
     */
    public String name;

    /**
     * ID for new users.
     */
    private static int idIndex = 0;

    /**
     * Constructor initiate name and ID.
     *
     * @param name user's name
     */
    public User(String name) {
        this.name = name;
        setId(String.valueOf(idIndex++));
    }

    /**
     * Getter for id.
     * @return id
     */
    @Override
    String getId() {
        return super.id;
    }

    /**
     * Setter for id.
     * @param id - id
     */
    @Override
    void setId(String id) {
        super.id = id;
    }

    /**
     * Equals by ID.
     * @param obj obj to compare
     * @return result of comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;

        User otherUser = (User) obj;

        return this.getId().equals(otherUser.getId());
    }

    /**
     * Hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
