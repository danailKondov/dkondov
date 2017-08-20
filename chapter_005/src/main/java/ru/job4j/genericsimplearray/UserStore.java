package ru.job4j.genericsimplearray;

import java.util.Iterator;

/**
 * Class for store users.
 *
 * @since 20/08/2017
 * @version 2
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Constructor initializing database.
     * @param size - size of database
     */
    public UserStore(int size) {
        super(new SimpleArray<>(size));
    }

    /**
     * String representation of object.
     * @return users names
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<User> iterator = store.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            sb.append(user.name);
            sb.append(" ");
        }
        return sb.toString();
    }
}
