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
 * @version 2
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
        User firstUser = new User("Ivan", "login", "pass", "admin", "email@email.ru", "Moscow", "Russia");
        store.add(firstUser);
        User secondUser = store.getUser(firstUser.getLogin());
        assertEquals(firstUser, secondUser);
    }

    /**
     * Tests update user in DB.
     */
    @Test
    public void updateTest() {
        User firstUser = new User("Ivan", "login", "pass", "admin", "email@email.ru", "Moscow", "Russia");
        store.add(firstUser);
        int firstUserId = firstUser.getUserID();
        User updatedUser = new User("Vasia", "newLogin", "newPass", "user", "newEmail@email.ru", "Paris", "France");
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
        User firstUser = new User("Ivan", "login", "pass", "admin", "email@email.ru", "Moscow", "Russia");
        User secondUser = new User("Vasia", "newLogin", "newPass", "user", "newEmail@email.ru", "Paris", "France");
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
        User firstUser = new User("Ivan", "login", "pass", "admin", "email@email.ru", "Moscow", "Russia");
        User secondUser = new User("Vasia", "newLogin", "newPass", "user", "newEmail@email.ru", "Paris", "France");
        store.add(firstUser);
        store.add(secondUser);
        store.delete("newLogin");
        List<User> usersList = store.getAllUsers();
        assertTrue(usersList.contains(firstUser));
        assertFalse(usersList.contains(secondUser));
    }

    /**
     * Tests method, which testing login and password.
     */
    @Test
    public void passwordAndLoginTest() {
        User firstUser = new User("Ivan", "login", "pass", "admin", "email@email.ru", "Moscow", "Russia");
        store.add(firstUser);
        assertTrue(store.loginAndPasswordIsValid("login", "pass"));
        assertFalse(store.loginAndPasswordIsValid("wronglogin", "pass"));
        assertFalse(store.loginAndPasswordIsValid("login", "wrongpass"));
        assertFalse(store.loginAndPasswordIsValid(null, "pass"));
        assertFalse(store.loginAndPasswordIsValid("login", null));
        assertFalse(store.loginAndPasswordIsValid(null, null));
    }

    /**
     * Tests is admin was added to DB during initialisation.
     */
    @Test
    public void adminWasAddedTest() {
        List<Role> roles = store.getAllRoles();
        boolean adminInDB = false;
        for (Role role : roles) {
            if (role.getRole().equals("admin")) adminInDB = true;
        }
        assertTrue(adminInDB);
    }

    /**
     * Tests adding and getting roles.
     */
    @Test
    public void addNewRoleTest() {
        Role role = new Role("user");
        store.addRole(role);
        assertTrue(store.getAllRoles().contains(role));
    }

    @Test
    public void deleteRoleTest() {
        Role role = new Role("roleToDelete");
        store.addRole(role);
        store.deleteRole(role.getRole());
        assertFalse(store.getAllRoles().contains(role));
    }

    @After
    public void cleanDB() {
        store.deleteAll();
    }
}