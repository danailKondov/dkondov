package ru.job4j.crud.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Class for data access test.
 *
 * @since 21/01/2018
 * @version 1
 */
public class UserStoreTest {

    private UserStore store;

    @Before
    public void init() {
        store = UserStore.getInstance();
    }

    /**
     * Tests adding and getting users.
     */
    @Test
    public void addAndGetUserTest() {
        User firstUser = new User("Ivan", "login", "email@email.ru");
        store.add(firstUser);
        User secondUser = store.getUser(firstUser.getLogin());
        assertEquals(firstUser, secondUser);
    }

    /**
     * Tests update user in DB.
     */
    @Test
    public void updateTest() {
        User firstUser = new User("Ivan", "login", "email@email.ru");
        store.add(firstUser);
        int firstUserId = firstUser.getUserID();
        User updatedUser = new User("Vasia", "newLogin", "newEmail@email.ru");
        updatedUser.setUserID(firstUserId);
        boolean isOk = store.update("login", updatedUser);
        User result = store.getUser("newLogin");
        assertEquals(updatedUser, result);
        assertTrue(isOk);
    }

    /**
     * Tests getting all users from DB.
     */
    @Test
    public void getAllUsersTest() {
        User firstUser = new User("Ivan", "login", "email@email.ru");
        User secondUser = new User("Vasia", "newLogin", "newEmail@email.ru");
        store.add(firstUser);
        store.add(secondUser);
        List<User> usersList = store.getAllUsers();
        assertTrue(usersList.contains(firstUser));
        assertTrue(usersList.contains(secondUser));
    }

    /**
     * Tests deleting users from DB.
     */
    @Test
    public void deleteTest() {
        User firstUser = new User("Ivan", "login", "email@email.ru");
        User secondUser = new User("Vasia", "newLogin", "newEmail@email.ru");
        store.add(firstUser);
        store.add(secondUser);
        store.delete("newLogin");
        List<User> usersList = store.getAllUsers();
        assertTrue(usersList.contains(firstUser));
        assertFalse(usersList.contains(secondUser));
    }

    @After
    public void cleanDB() {
        store.deleteAll();
    }
}