package ru.job4j.simplecounter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class plain simple counter.
 *
 * @since 28/09/2017
 * @version 1
 */
@ThreadSafe
public class Counter {

    /**
     * Result of increment ops.
     */
    @GuardedBy("this")
    private int result = 0;

    /**
     * Adds one to result.
     * @return result of increment
     */
    public synchronized int increment() {
        result++;
        return result;
    }
}
