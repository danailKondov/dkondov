package ru.job4j.simpleuserstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

/**
 * Class is storage for users.
 *
 * @since 30/09/2017
 * @version 1
 */
@ThreadSafe
public class UserStorage {

    /**
     * Container for users.
     */
    @GuardedBy("this")
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Adds new user to storage.
     * @param user to add
     * @return true if operation was successful
     */
    public synchronized boolean add(User user) {
        boolean result = false;
        if (!users.contains(user)) {
            users.add(user);
            result = true;
        }
        return result;
    }

    /**
     * Updates user in storage.
     * @param user to update
     */
    public synchronized void update(User user) {
        int newUserID = user.getId();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == newUserID) {
                users.set(i, user);
                break;
            }
        }
    }

    /**
     * Removes user from storage.
     * @param user to remove
     */
    public synchronized void delete(User user) {
        users.remove(user);
    }

    /**
     * Transfers some amount from one user to another.
     *
     * @param fromId id of user to transfer from
     * @param toId id of user to transfer
     * @param amount to transfer
     * @return true if operation was successful
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;

        User fromUser = null;
        User toUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == fromId) {
                fromUser = users.get(i);
            }
            if (users.get(i).getId() == toId) {
                toUser = users.get(i);
            }
        }

        // проверка участников транзакции
        if (fromId != toId && fromUser != null && toUser != null && fromUser.getAmount() >= amount) {

            // транзакция
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
            result = true;
        }

        return result;
    }

    /**
     * Getter for test purposes only
     *
     * @return list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
