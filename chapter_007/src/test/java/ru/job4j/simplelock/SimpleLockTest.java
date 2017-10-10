package ru.job4j.simplelock;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for test simple lock.
 *
 * @since 10/10/2017
 * @version 1
 */
public class SimpleLockTest {

    SimpleLock lock = new SimpleLock();

    public class Increment {
        int sum = 0;

        public void go() {
            lock.lock();
            sum++;
            lock.unlock();
        }
    }

    /**
     * Tests DIY lock.
     */
    @Test
    public void lockTest() {
        SimpleLockTest test = new SimpleLockTest();
        SimpleLockTest.Increment increment = test.new Increment();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500_000; i++) {
                    increment.go();
                }
            }
        };

        Thread threadOne = new Thread(runnable);
        Thread threadTwo = new Thread(runnable);

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         assertThat(increment.sum, is (1_000_000));
    }
}