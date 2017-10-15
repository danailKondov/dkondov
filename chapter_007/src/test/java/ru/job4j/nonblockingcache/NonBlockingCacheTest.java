package ru.job4j.nonblockingcache;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for test simple DIY non-blocking cache.
 *
 * @since 15/10/2017
 * @version 1
 */
public class NonBlockingCacheTest {

    volatile boolean optimisticExceptionIsOn = false;

    /**
     * Tests exception in case of concurrent overwriting
     */
    @Test
    public void nonblockingOptimisticExceptionTest() {
        NonBlockingCache cache = new NonBlockingCache();
        Task task = new Task("task 1");
        int firstTaskId = task.getiD();
        cache.add(task);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    task.setVersion(task.getVersion() + 1);
                    task.setName("modified by thread " + Thread.currentThread().getId() + "; version " + task.getVersion());
                    try {
                        cache.update(task);
                    } catch (OptimisticException e) {
                        optimisticExceptionIsOn = true;
                        break;
                    }
                }
            }
        };

        Thread firstThread = new Thread(r);
        Thread secondThread = new Thread(r);

        firstThread.start();
        secondThread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        firstThread.interrupt();
        secondThread.interrupt();

        assertTrue(optimisticExceptionIsOn);
    }
}