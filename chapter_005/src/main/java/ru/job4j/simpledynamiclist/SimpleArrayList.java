package ru.job4j.simpledynamiclist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Class simple array list.
 *
 * @since 02/10/2017
 * @version 2
 */
@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {

    /**
     * Contains elements.
     */
    @GuardedBy("lock")
    private Object[] container;

    /**
     * Points to last element.
     */
    @GuardedBy("lock")
    private int pointer = 0;

    /**
     * Lock for synchronization.
     */
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Lock for read.
     */
    private Lock readLock = lock.readLock();

    /**
     * Lock for write.
     */
    private Lock writeLock = lock.writeLock();

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
        writeLock.lock();
        try {
            if (pointer < container.length) {
                container[pointer++] = value;
            } else {
                Object[] newContainer = new Object[container.length + container.length / 2];
                System.arraycopy(container, 0, newContainer, 0, container.length);
                container = newContainer;
                container[pointer++] = value;
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Get new value by index.
     * @param index - index
     * @return value
     */
    public E get(int index) {
        readLock.lock();
        try {
            if (index >= pointer) {
                throw new IndexOutOfBoundsException();
            }
            return (E) container[index];
        } finally {
            readLock.unlock();
        }
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
                readLock.lock();
                try {
                    return index != pointer;
                } finally {
                    readLock.unlock();
                }
            }

            @Override
            public E next() {
                readLock.lock();
                try {
                    if(index >= pointer) {
                        throw new NoSuchElementException();
                    }
                    return (E) container[index++];
                } finally {
                    readLock.unlock();
                }
            }
        };
    }
}
