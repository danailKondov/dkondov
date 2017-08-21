package ru.job4j.simpledynamiclist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple array list.
 *
 * @since 21/08/2017
 * @version 1
 */
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Contains elements.
     */
    private Object[] container;

    /**
     * Points to last element.
     */
    private int pointer = 0;

    /**
     * Default constructor.
     */
    public SimpleArrayList() {
        this.container = new Object[10];
    }

    /**
     * Add new element.
     * @param value - value to add
     */
    public void add(E value) {
        if (pointer < container.length) {
            container[pointer++] = value;
        } else {
            Object[] newContainer = new Object[container.length + container.length / 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
            container[pointer++] = value;
        }
    }

    /**
     * Get new value by index.
     * @param index - index
     * @return value
     */
    public E get(int index) {
        if (index >= pointer) {
            throw new IndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    /**
     * Iterator.
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index != pointer;
            }

            @Override
            public E next() {
                if(index >= pointer) {
                    throw new NoSuchElementException();
                }
                return (E) container[index++];
            }
        };
    }
}
