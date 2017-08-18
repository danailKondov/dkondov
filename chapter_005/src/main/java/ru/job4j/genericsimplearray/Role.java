package ru.job4j.genericsimplearray;

/**
 * Class role.
 *
 * @since 18/08/2017
 * @version 1
 */
public class Role extends Base {

    /**
     * Roles's description.
     */
    public String role;

    /**
     * ID for new users.
     */
    private static int idIndex = 0;

    /**
     * Constructor initiate role and ID.
     *
     * @param role role
     */
    public Role(String role) {
        this.role = role;
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
        if (!(obj instanceof Role)) return false;

        Role otherRole = (Role) obj;

        return this.getId().equals(otherRole.getId());
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
