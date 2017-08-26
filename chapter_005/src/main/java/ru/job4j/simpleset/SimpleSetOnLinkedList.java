package ru.job4j.simpleset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple set on linked list.
 *
 * @since 25/08/2017
 * @version 1
 */
public class SimpleSetOnLinkedList<E> implements Iterable<E>, SimpleSets<E> {

    /**
     * First element.
     */
    private Node<E> first;

    /**
     * Last element.
     */
    private Node<E> last;

    /**
     * List's size;
     */
    private int size = 0;

    /**
     * Add new element to the end of list.
     * @param e - new element
     * @return false if operation is unsuccessful
     */
    public boolean add(E e) {
        boolean result = false;
        if(!contains(e)) {
            if(size == 0) {
                Node<E> newNode = new Node<>(e);
                first = last = newNode;
                size++;
                result = true;
            } else {
                Node<E> newNode = new Node<>(e);
                last.next = newNode;
                last = newNode;
                size++;
                result = true;
            }
        }
        return result;
    }

    /**
     * Tests is there element in this set.
     * @param e element
     * @return true if set contains this element
     */
    public boolean contains(E e) {
        boolean result = false;
        for (E elem : this) {
            if (elem.equals(e)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Iterator
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            int pointer = 0;
            Node<E> x = null;

            @Override
            public boolean hasNext() {
                return first != null && pointer != size;
            }

            @Override
            public E next() {
                if(first == null || pointer == size) {
                    throw new NoSuchElementException();
                }

                if (pointer == 0) {
                    x = first;
                    pointer++;
                } else {
                    x = x.next;
                    pointer++;
                }
                return x.data;
            }
        };
    }

    /**
     * Class is node in linked list.
     * @param <E> is type of data
     */
    private class Node<E> {

        /**
         * Next element.
         */
        public Node<E> next = null;

        /**
         * Data.
         */
        public E data;

        /**
         * Constructor.
         * @param data data
         */
        public Node(E data) {
            this.data = data;
        }
    }
}
