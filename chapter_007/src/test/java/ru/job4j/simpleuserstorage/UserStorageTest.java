package ru.job4j.simpleuserstorage;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests user storage.
 *
 * @since 30/09/2017
 * @version 1
 */
public class UserStorageTest {

    /**
     * Tests add ops.
     */
    @Test
    public void addTest() {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 100));
        storage.add(new User(1, 200));
        storage.add(new User(2, 300));
        storage.add(new User(3, 400));

        assertEquals(storage.getUsers().size(), 3);
    }

    /**
     * Tests delete ops.
     */
    @Test
    public void deleteTest() {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 200));
        storage.add(new User(2, 300));
        storage.add(new User(3, 400));
        storage.delete(new User (1, 200));

        assertEquals(storage.getUsers().size(), 2);
    }

    /**
     * Tests update ops.
     */
    @Test
    public void updateTest() {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 200));
        storage.add(new User(2, 300));
        storage.add(new User(3, 400));
        storage.update(new User(1, 500));

        int actualAmount = 0;
        for (User user : storage.getUsers()) {
            if (user.getId() == 1) {
                actualAmount = user.getAmount();
            }
        }

        assertEquals(actualAmount, 500);
    }

    /**
     * Tests concurrent transfer ops.
     */
    @Test
    public void multipleTransferTest() {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 200));
        storage.add(new User(2, 300));
        storage.add(new User(3, 400));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    int idFrom = (int) (Math.random() * 3 + 1); // 1...3
                    int idTo = (int) (Math.random() * 3 + 1);
                    int amount = (int) (Math.random() * 400); // 0...400
                    storage.transfer(idFrom, idTo, amount);
                }
            }
        };

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(thread);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }

        int actualSumAmount = 0;
        for (User user : storage.getUsers()) {
            actualSumAmount += user.getAmount();
        }

        System.out.println(actualSumAmount);
        assertEquals(actualSumAmount, 900);
    }

}