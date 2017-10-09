package ru.job4j.simplethreadpool;

/**
 * Class for testing simple thread pool.
 *
 * @since 08/10/2017
 * @version 1
 */
public class Work {

    /**
     * Imitate some work.
     */
    public void someWork() {
        System.out.println("Some work is done by thread " + Thread.currentThread().getId());
    }
}
