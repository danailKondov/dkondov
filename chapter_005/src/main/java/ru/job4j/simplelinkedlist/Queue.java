package ru.job4j.simplelinkedlist;

import java.util.NoSuchElementException;

/**
 * Class simple queue.
 *
 * @since 22/08/2017
 * @version 1
 */
public class Queue<E> {

    /**
     * Container for queue.
     */
    private SimpleLinkedList<E> container = new SimpleLinkedList<E>();

    /**
     * Adds new element to this queue.
     * @param e - new element to add
     */
    public void add(E e) {
        container.add(e);
    }

    /**
     * Retrieves and removes the head of this queue.
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E poll() {
        return container.remove(0);
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public E element() {
        try {
            return container.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    /**
     * Tests is this queue is empty.
     * @return result of test
     */
    public boolean isEmpty() {
        return container.size() == 0;
    }
}
