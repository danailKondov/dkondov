package ru.job4j.genericsimplearray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing simple generic.
 *
 * @since 19/08/2017
 * @version 1
 **/
public class UserStoreTest {

    /**
     * Test for add and remove methods.
     */
    @Test
    public void addRemoveTest() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("Tatiana");
        User user2 = new User("Serge");
        userStore.add(user1);
        userStore.add(user2);
        userStore.remove(user2);
        String result = userStore.toString();
        String expected = "Tatiana ";
        assertThat(result, is(expected));
    }

    /**
     * Test for add and update methods.
     */
    @Test
    public void addUpdateTest() {
        UserStore userStore = new UserStore(10);
        User user1 = new User("Tatiana");
        User user2 = new User("Serge");
        userStore.add(user1);
        userStore.add(user2);

        String id = user2.getId();
        User userUpdated2 = new User("Pasha");
        userUpdated2.setId(id);
        userStore.update(userUpdated2);

        String result = userStore.toString();
        String expected = "Tatiana Pasha ";
        assertThat(result, is(expected));
    }
}