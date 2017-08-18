package ru.job4j.genericsimplearray;

import java.util.Iterator;

/**
 * Class for store users.
 *
 * @since 18/08/2017
 * @version 1
 */
public class UserStore implements Store<User> {

    /**
     * Database for users.
     */
    private SimpleArray<User> store;

    /**
     * Constructor initializing database.
     * @param size - size of database
     */
    public UserStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * Add new user.
     * @param user new user
     */
    public void add(User user) {
        store.add(user);
    }

    /**
     * Remove user.
     * @param user - user to remove
     */
    public void remove(User user) {
        Integer position = store.getPosition(user);
        if (position != null) {
            store.delete(position);
        }

    }

    /**
     * Update user by ID
     * @param user user to update
     */
    public void update(User user) {
        // непонятно условие задачи: "Помните про инкапсуляцию. В методах store нельзя
        // использовать методы c index". - Как модифицировать без индекса - т.е. как скопировать
        // нового user'a на место старого не зная этого "места"? Как мне кажется,
        // индекс может не знать пользователь UserStore, но сам метод знать его обязан. Для
        // этого я добавил метод getPosition(user) и вспомогательный equals() и hashCode() в SimpleArray.
        Integer position = store.getPosition(user);
        if (position != null) {
            store.update(position, user);
        }
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
            sb.append(iterator.next().name);
            sb.append(" ");
        }
        return sb.toString();
    }
}
