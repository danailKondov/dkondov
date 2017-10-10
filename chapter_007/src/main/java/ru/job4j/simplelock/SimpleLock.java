package ru.job4j.simplelock;

/**
 * Class for simple DIY lock.
 *
 * @since 10/10/2017
 * @version 1
 */
public class SimpleLock {

    /**
     * True if lock is owned.
     */
    private boolean lockIsOwned = false;

    /**
     * Performs lock.
     */
    public void lock() {
        synchronized (this) {
            try {
                while(lockIsOwned) {
                    wait();
                }
                lockIsOwned = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs unlock.
     */
    public void unlock() {
        synchronized (this) {
            lockIsOwned = false;
            notify();
        }
    }
}
