package ru.job4j.simplelinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple linked list.
 *
 * @since 21/08/2017
 * @version 1
 */
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * First element.
     */
    private Link<E> first;

    /**
     * Last element.
     */
    private Link<E> last;

    /**
     * List's size;
     */
    private int size = 0;

    /**
     * Default constructor.
     */
    public SimpleLinkedList() {
        first = null;
        last = null;
    }

    /**
     * Add new element to the end of list.
     * @param e - new element
     */
    public void add(E e) {
        if (size == 0) {
            Link<E> newLink = new Link<E>(e);
            first = last = newLink;
            size++;
        } else {
            Link<E> newLink = new Link<E>(e);
            last.next = newLink;
            last = newLink;
            size++;
        }
    }

    /**
     * Get element by index.
     * @param index - index of element
     * @return data stored in element
     */
    public E get(int index) {
        if (index >=0 && index < size) {
            Link<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.data;
        }
            throw new IndexOutOfBoundsException();
    }

    /**
     * Iterator
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int pointer = 0;
            Link<E> x = null;

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
}
