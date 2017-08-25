package ru.job4j.simpleset;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class simple set.
 *
 * @since 24/08/2017
 * @version 1
 */
public class SimpleSet<E> implements Iterable<E>{

    /**
     * Contains elements.
     */
    private Object[] container = new Object[10];

    /**
     * Points to last element.
     */
    private int pointer = 0;

    /**
     * Add new element.
     * @param e - value to add
     */
    public void add(E e) {

        // проверяем аргумент на null
        Objects.requireNonNull(e);

        // увеличиваем массив при переполнении
        if(pointer == container.length) {
            Object[] newContainer = new Object[container.length + container.length / 2];
            System.arraycopy(container, 0, newContainer, 0, container.length);
            container = newContainer;
        }

        // проверяем на совпадения и добавляем элемент
        if (pointer == 0) {
            container[pointer++] = e;
        } else {
            if (!contains(e)) {
                container[pointer++] = e;
            }
        }

    }

    /**
     * Tests is there element in this set.
     * @param e element
     * @return true if set contains this element
     */
    public boolean contains(E e) {
        boolean result = false;
        for (E element : this) {
            if(element.equals(e)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Iterator.
     * @return iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int count;

            @Override
            public boolean hasNext() {
                return count != pointer;
            }

            @Override
            public E next() {
                try {
                    return (E) container[count++];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
