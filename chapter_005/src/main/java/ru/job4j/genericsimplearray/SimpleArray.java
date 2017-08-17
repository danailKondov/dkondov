package ru.job4j.genericsimplearray;

/**
 * Class is wrapper for array and is used as example for generic.
 *
 * @since 17/08/2017
 * @version 1
 */
public class SimpleArray<E> {

    /**
     * Array of objects.
     */
    private Object[] objects;

    /**
     * Index of values.
     */
    private int index = 0;

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
        objects[index++] = value;
    }

    /**
     * Get value by index.
     * @param position - index of value
     * @return - value
     */
    public E get(int position) {
        return (E) objects[position];
    }

    /**
     * Update by index with new value
     * @param position - index
     * @param value - value
     */
    public void update(int position, E value) {
        objects[position] = value;
    }

    /**
     * Delete value by index.
     * @param position - index
     */
    public void delete(int position) {
        objects[position] = null;
    }
}
