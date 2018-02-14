package ru.job4j.crud.model;

/**
 * Class represents simple user.
 *
 * @since 13/02/2018
 * @version 1
 */
public class Role {

    private int id;
    private String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(String role) {
        this.role = role;
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (getId() != role1.getId()) return false;
        return getRole() != null ? getRole().equals(role1.getRole()) : role1.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }
}
