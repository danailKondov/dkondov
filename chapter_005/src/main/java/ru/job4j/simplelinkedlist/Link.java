package ru.job4j.simplelinkedlist;

/**
 * Class container for data in simple linked list.
 *
 * @since 21/08/2017
 * @version 1
 */
public class Link<E> {

    /**
     * Data.
     */
    public E data;

    /**
     * Connection to next link.
     */
    public Link<E> next;

    /**
     * Constructor.
     *
     * @param data - data to store
     */
    public Link(E data) {
        this.data = data;
        next = null;
    }
}
