package ru.job4j.taskfortest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class user's account manager.
 *
 * @since 12/08/2017
 * @version 1
 */
public class AccountManager {

    /**
     * Database of users and their accounts.
     */
    private Map<User, List<Account>> usersAccounts = new HashMap<>();

    /**
     * Getter for database.
     * @return database
     */
    public Map<User, List<Account>> getDB() {
        return usersAccounts;
    }

    /**
     * Gets user by name.
     * @param name - user's name
     * @return - user
     */
    public User getUserByName(String name) {
        User result = null;
        for (User user : usersAccounts.keySet()) {
            if (user.getName().equals(name)) {
                result = user;
            }
        }
        return result;
    }

    /**
     * Gets account by requisites.
     * @param requisites - requisites
     * @return - account
     */
    public Account getAccountByRequisites(int requisites) {
        Account result = null;
        for (List<Account> acc : usersAccounts.values()) {
            for (Account account : acc) {
                if (account.getRequisites() == requisites) {
                    result = account;
                }
            }
        }
        return result;
    }

    /**
     * Adding new user.
     * @param user - new user to add
     */
    public void addUser(User user) {
        if (!(usersAccounts.keySet().contains(user))) {
            usersAccounts.put(user, null);
        }
    }

    /**
     * Deleting user.
     * @param user - user to delete
     */
    public void deleteUser(User user) {
        usersAccounts.remove(user);
    }

    /**
     * Adding new account to user.
     * @param user - user
     * @param account - new account
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> list = usersAccounts.get(user);
        if (list == null) {
            list = new ArrayList<>();
        }
        if(!(list.contains(account))) {
            list.add(account);
            usersAccounts.put(user, list);
        } else {
            System.out.println("Operation denied! This account was previously added");
        }
    }

    /**
     * Deleting user's account.
     * @param user - user
     * @param account - account to delete
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> list = usersAccounts.get(user);
        list.remove(account);
        usersAccounts.put(user, list);
    }

    /**
     * Getter for list of user's accounts.
     * @param user - user
     * @return - list of accounts
     */
    public List<Account> getUserAccounts (User user) {
        return usersAccounts.get(user);
    }

    /**
     * Transfer of money from one account to another.
     * @param srcUser - user to transfer
     * @param srcAccount - source account
     * @param dstUser - user endpoint of transfer
     * @param dstAccount - destination account
     * @param amount - amount to transfer
     * @return - result of transfer
     */
    public boolean transferMoney (User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        if (srcAccount.getValue() >= amount && getUserAccounts(srcUser).contains(srcAccount)) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            result = true;
        }
        return result;
    }
}
