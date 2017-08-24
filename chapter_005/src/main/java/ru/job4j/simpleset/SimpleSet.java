package ru.job4j.simpleset;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
            for (int i = 0; i < pointer; i++) {
                if (container[i].equals(e)) {
                    return;
                }
            }
            container[pointer++] = e;
        }

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
