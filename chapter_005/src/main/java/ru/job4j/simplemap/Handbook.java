package ru.job4j.simplemap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class simple hash map. Collision is not realised.
 *
 * @since 29/08/2017
 * @version 1
 */
public class Handbook<T, V> implements Iterable<T>{

    /**
     * Contains elements.
     */
    private Object[] container;

    /**
     * Counts elements in table.
     */
    private int size = 0;

    /**
     * Constructor with size parameter.
     * @param size is length of array container
     */
    public Handbook(int size) {
        container = new Object[size];
    }

    /**
     * Inserts new pair key-value to handbook/
     *
     * @param key key
     * @param value value
     * @return true if operation is successful
     * @throws NullPointerException if key is null
     * @throws IndexOutOfBoundsException if number of pairs is bigger than size of container
     */
    public boolean insert(T key, V value) {
        Objects.requireNonNull(key);

        int index = getArrayIndex(key);
        boolean result = false;
        if (container[index] == null) {
            ensureCapacity();
            container[index] = new Entry(key, value);
            size++;
            result = true;
        }
        return result;
    }

    /**
     * Calculates array index using hash code.
     *
     * @param key key
     * @return array index
     */
    private int getArrayIndex(T key) {
        return (key.hashCode()) % container.length;
    }

    /**
     * Test is there enough place for new element.
     * @throws IndexOutOfBoundsException in case container is full
     */
    private void ensureCapacity() {
        if (size == container.length) {
            // в нормальной карте здесь метод, увеличивающий контейнер
            // и заново перераспределяющий элементы, у нас - исключение
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Gets value by key.
     *
     * @param key key
     * @return value or null
     * @throws NullPointerException if key is null
     */
    public V get(T key) {
        Objects.requireNonNull(key);
        int index = getArrayIndex(key);
        Entry entry = (Entry)container[index];
        return entry.value;
    }

    /**
     * Deletes value by key.
     *
     * @param key key
     * @return true if operation is successful
     * @throws NullPointerException if key is null
     */
    public boolean delete(T key) {
        Objects.requireNonNull(key);
        int index = getArrayIndex(key);
        boolean result = false;
        if (container[index] != null) {
            container[index] = null;
            size--;
            result = true;
        }
        return result;
    }

    /**
     * Class container for pair key-value.
     */
    private class Entry {
        public T key;
        public V value;

        public Entry(T t, V v) {
            this.key = t;
            this.value = v;
        }

        @Override
        public String toString() {
            return key.toString() + " = " + value.toString();
        }
    }

    /**
     * Iterator for keys.
     *
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int pointer = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                for (int i = pointer; i < container.length; i++) {
                    if (container[i] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public T next() {
                for (int i = pointer; i < container.length; i++) {
                    if (container[i] != null) {
                        Entry entry = (Entry)container[i];
                        pointer = ++i;
                        return entry.key;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }
}
