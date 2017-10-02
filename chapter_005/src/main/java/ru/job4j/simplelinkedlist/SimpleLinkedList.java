package ru.job4j.simplelinkedlist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Class simple linked list. For better use as container
 * for stack we will make it doubly-linked and bilateral
 *
 * @since 22/08/2017
 * @version 2
 */
@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * First element.
     */
    @GuardedBy("rwl")
    private Link<E> first;

    /**
     * Last element.
     */
    @GuardedBy("rwl")
    private Link<E> last;

    /**
     * List's size;
     */
    @GuardedBy("rwl")
    private int size = 0;

    /**
     * Lock for synchronization.
     */
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    /**
     * Lock for read.
     */
    private final Lock readLock = rwl.readLock();

    /**
     * Lock for write.
     */
    private final Lock writelock = rwl.writeLock();

    /**
     * Default constructor.
     */
    public SimpleLinkedList() {
        first = null;
        last = null;
    }

    /**
     * Getter for size.
     * @return size of container
     */
    public int size() {
        readLock.lock();
        try {
            return size;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Add new element to the end of list.
     * @param e - new element
     */
    public void add(E e) {
        writelock.lock();
        try {
            if (size == 0) {
                Link<E> newLink = new Link<E>(e);
                first = last = newLink;
                size++;
            } else {
                Link<E> newLink = new Link<E>(e);
                last.next = newLink;
                newLink.previous = last;
                last = newLink;
                size++;
            }
        } finally {
            writelock.unlock();
        }
    }

    /**
     * Get element by index.
     * @param index - index of element
     * @return data stored in element
     */
    public E get(int index) {
        writelock.lock();
        try {
            if (index >=0 && index < size) {
                Link<E> x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                return x.data;
            }
            throw new IndexOutOfBoundsException();
        } finally {
            writelock.unlock();
        }
    }

    /**
     * Remove last element and return it.
     * @return removed last element or null if absent
     * @throws NoSuchElementException if no element to remove
     */
    public E remove() {
        writelock.lock();
        try {
            Link<E> result = null;
            if(size > 1) {
                result = last;
                last = last.previous;
                last.next = null;
                size--;
            } else if (size == 1) {
                result = last;
                last = first = null;
                size--;
            } else {
                throw new NoSuchElementException();
            }
            return result.data;
        } finally {
            writelock.unlock();
        }
    }

    /**
     * Removes element by index.
     * @param index of element to remove
     * @return removed element
     * @throws IndexOutOfBoundsException if index is out of bounds of container
     * @throws NoSuchElementException if no element to remove
     */
    public E remove(int index) {
        writelock.lock();
        try {
            Link<E> result = null;

            if(size == 0) throw new NoSuchElementException();

            if (index == 0) {
                if (size > 1) {
                    result = first;
                    first = first.next;
                    first.previous = null;
                    size--;
                } else if (size == 1) {
                    return remove();
                }
            } else if (index > 0 && index < size - 1) {
                Link<E> x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                result = x;
                x.previous.next = x.next;
                x.next.previous = x.previous;
                size--;
            } else if (index == size - 1) {
                return remove();
            } else {
                throw new IndexOutOfBoundsException();
            }

            return result.data;
        } finally {
            writelock.unlock();
        }
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
                readLock.lock();
                try {
                    return first != null && pointer != size;
                } finally {
                    readLock.unlock();
                }
            }

            @Override
            public E next() {
                readLock.lock();
                try {
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
                } finally {
                    readLock.unlock();
                }
            }
        };
    }
}
