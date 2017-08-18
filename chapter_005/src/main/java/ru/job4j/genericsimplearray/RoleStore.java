package ru.job4j.genericsimplearray;

/**
 * Class for store roles.
 *
 * @since 18/08/2017
 * @version 1
 */
public class RoleStore implements Store<Role> {

    /**
     * Database for users.
     */
    private SimpleArray<Role> store;

    /**
     * Constructor initializing database.
     * @param size - size of database
     */
    public RoleStore(int size) {
        this.store = new SimpleArray<>(size);
    }

    /**
     * Add new role.
     * @param role new role
     */
    public void add(Role role) {
        store.add(role);
    }

    /**
     * Remove user.
     * @param role - role to remove
     */
    public void remove(Role role) {
        Integer position = store.getPosition(role);
        if (position != null) {
            store.delete(position);
        }

    }

    /**
     * Update user by ID
     * @param role role to update
     */
    public void update(Role role) {
        // непонятно условие задачи: "Помните про инкапсуляцию. В методах store нельзя
        // использовать методы c index". - Как модифицировать без индекса - т.е. как скопировать
        // нового user'a на место старого не зная этого "места"? Как мне кажется,
        // индекс может не знать пользователь UserStore, но сам метод знать его обязан. Для
        // этого я добавил метод getPosition(user) и вспомогательный equals() и hashCode() в SimpleArray.
        Integer position = store.getPosition(role);
        if (position != null) {
            store.update(position, role);
        }

    }

}
