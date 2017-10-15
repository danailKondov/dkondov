package ru.job4j.nonblockingcache;

/**
 * Class for optimistic exception.
 *
 * @since 11/10/2017
 * @version 1
 */
public class OptimisticException extends Exception {
    public OptimisticException() {
        super("Object was already modified");
    }
}
