package ru.job4j.genericsimplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class is wrapper for array and is used as example for generic.
 *
 * @since 18/08/2017
 * @version 2
 */
public class SimpleArray<E> implements Iterable<E> {

    /**
     * Array of objects.
     */
    private Object[] objects;

    /**
     * Index of values.
     */
    private int index = 0;

    /**
     * Number of items.
     */
    private int numOfItems = 0;

    /**
     * Constructor.
     * @param size - size of array of objects
     */
    public SimpleArray(int size) {
        objects = new Object[size];
    }

    /**
     * Add new value to array.
     * @param value - new value
     */
    public void add(E value) {
        if (index < objects.length) {
            objects[index++] = value;
            numOfItems++;
        }
    }

    /**
     * Get value by index.
     * @param position - index of value
     * @return - value or null
     */
    public E get(int position) {
        E result = null;
        if (position >= 0 && position < objects.length) {
            result = (E) objects[position];
        }
        return result;
    }

    /**
     * Getter for position of value.
     * @param value - position to find
     * @return - position or null if absent
     */
    public Integer getPosition(E value) {
        Integer result = null;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null && objects[i].equals(value)) { // equals есть у любого объекта, но требуется переопределение
                result = i;
            }
        }
        return result;
    }

    /**
     * Update by index with new value
     * @param position - index
     * @param value - value
     */
    public void update(int position, E value) {
        if (position >= 0 && position < objects.length) {
            objects[position] = value;
        }
    }

    /**
     * Delete value by index.
     * @param position - index
     */
    public void delete(int position) {
        if (position >= 0 && position < objects.length) {
            objects[position] = null;
            numOfItems--;
        }
    }

    /**
     * Iterator.
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int position = 0;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return position < objects.length && count < numOfItems;
            }

            @Override
            public E next() {
                E result;
                while(true) {
                    if (position == objects.length) {
                        throw new NoSuchElementException();
                    }
                    result = (E) objects[position++];
                    if (result != null) {
                        count++;
                        return result;
                    }
                }
            }

            @Override
            public void remove() {
                delete(position);
            }
        };
    }
}
