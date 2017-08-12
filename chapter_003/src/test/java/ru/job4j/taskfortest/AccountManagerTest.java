package ru.job4j.taskfortest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;


/**
 * Class for testing array to list and back conversion.
 * @since 10/08/2017
 * @version 1
 **/
public class AccountManagerTest {

    /**
     * Add user and account ops test.
     */
    @Test
    public void addUserAndAccountsTest() {
        AccountManager manager = new AccountManager();
        User user1 = new User("Sasha", 12456578);
        User user2 = new User("Vasia", 78564523);
        manager.addUser(user1);
        manager.addUser(user2);
        Account account1 = new Account(15000, 15264879);
        Account account2 = new Account(30000, 45897856);
        manager.addAccountToUser(manager.getUserByName("Sasha"), account1);
        manager.addAccountToUser(manager.getUserByName("Vasia"), account2);
        Map<User, List<Account>> result = manager.getDB();
        List<Account> expected = new ArrayList<>();
        expected.add(account1);
        assertThat(result.get(user1), is (expected));
    }

    /**
     * Delete user and account ops test.
     */
    @Test
    public void deleteUserAndAccountsTest() {
        AccountManager manager = new AccountManager();
        User user1 = new User("Sasha", 12456578);
        User user2 = new User("Vasia", 78564523);
        manager.addUser(user1);
        manager.addUser(user2);
        Account account1 = new Account(15000, 15264879);
        Account account2 = new Account(30000, 45897856);
        manager.addAccountToUser(manager.getUserByName("Sasha"), account1);
        manager.addAccountToUser(manager.getUserByName("Vasia"), account2);
        manager.deleteAccountFromUser(manager.getUserByName("Sasha"), manager.getAccountByRequisites(15264879)); // будет пустой список
        manager.deleteUser(manager.getUserByName("Vasia"));
        Map<User, List<Account>> result = manager.getDB();
        assertThat(result.get(user1).size(), is (0));
        assertThat(result.keySet().contains(user2), is (false));
    }

    /**
     * Transfer ops test.
     */
    @Test
    public void transferTest() {
        AccountManager manager = new AccountManager();
        User user1 = new User("Sasha", 12456578);
        User user2 = new User("Vasia", 78564523);
        manager.addUser(user1);
        manager.addUser(user2);
        Account account1 = new Account(15000, 15264879);
        Account account2 = new Account(30000, 45897856);
        manager.addAccountToUser(manager.getUserByName("Sasha"), account1);
        manager.addAccountToUser(manager.getUserByName("Vasia"), account2);
        manager.transferMoney(user1, account1, user2, account2, 10000);
        Map<User, List<Account>> result = manager.getDB();
        assertThat(result.get(user1).get(0).getValue(), closeTo (5000, 0.1));
    }

    /**
     * Transfer ops test for fail.
     */
    @Test
    public void failedTransferTest() {
        AccountManager manager = new AccountManager();
        User user1 = new User("Sasha", 12456578);
        User user2 = new User("Vasia", 78564523);
        manager.addUser(user1);
        manager.addUser(user2);
        Account account1 = new Account(15000, 15264879);
        Account account2 = new Account(30000, 45897856);
        manager.addAccountToUser(manager.getUserByName("Sasha"), account1);
        manager.addAccountToUser(manager.getUserByName("Vasia"), account2);
        boolean result = manager.transferMoney(user1, account1, user2, account2, 20000);
        assertThat(result, is(false));
    }

}