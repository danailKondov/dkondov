package ru.job4j.simpleset;

/**
 * Interface for simple sets.
 *
 * @since 26/08/2017
 * @version 1
 */
public interface SimpleSets<E> {

    /**
     * Add new element.
     * @param e - value to add
     * @return false if operation is unsuccessful (if element is already in)
     */
    boolean add(E e);

    /**
     * Tests is there element in this set.
     * @param e element
     * @return true if set contains this element
     */
    boolean contains(E e);
}
