package ru.job4j.genericsimplearray;

import java.util.Iterator;

/**
 * Class for store roles.
 *
 * @since 20/08/2017
 * @version 2
 */
public class RoleStore extends AbstractStore<Role> {

    /**
     * Constructor initializing database.
     * @param size - size of database
     */
    public RoleStore(int size) {
        super(new SimpleArray<>(size));
    }

    /**
     * String representation of object.
     * @return users names
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Role> iterator = store.iterator();
        while(iterator.hasNext()) {
            Role role = iterator.next();
            sb.append(role.role);
            sb.append(" ");
        }
        return sb.toString();
    }

}
