package ru.job4j.simpleset;

import java.util.Iterator;
import java.util.Objects;

/**
 * Class simple hash set.
 *
 * @since 26/08/2017
 * @version 1
 */
public class SimpleHashSet<E> implements SimpleSets<E>{

    /**
     * Contains elements.
     */
    private Object[] container;

    /**
     * Counts elements in table.
     */
    private int size = 0;

    /**
     * Constructor with size parametr.
     * @param size is length of array container
     */
    public SimpleHashSet(int size) {
        this.container = new Object[size];
    }

    /**
     * Default constructor with array length = 50000.
     */
    public SimpleHashSet() {
        this(50000);
    }

    /**
     * Add new element.
     * @param e - value to add
     * @return false if operation is unsuccessful (if element is already in)
     */
    public boolean add(E e) {
        // проверяем аргумент на null и выбрасываем NPE если проверка положительная
        Objects.requireNonNull(e);

        // Hash-table.
        // При коллизии используем метод цепочек, т.е. используем связный
        // список для хранения данных в "корзинах"
        int i = getArrayIndex(e);
        boolean result = true;
        if (container[i] == null) {
            container[i] = new Node<>(e);
            size++;
        } else {
            Node<E> x = (Node<E>) container[i];
            if(x.data.equals(e)) return false;  // отсекаем повторения в первом Nod'e
            while(x.next != null) {
                if(x.data.equals(e)) { // отсекаем повторения в последующих Nod'ах
                    result = false;
                    break;
                }
                x = x.next;
            }
            if (result) {
                x.next = new Node<>(e);
                size++;
            }
        }
        return result;
    }

    /**
     * Calculates array index using hash code.
     * @param e - element index to calculate
     * @return array index of element
     */
    private int getArrayIndex(E e) {
        return Math.abs(e.hashCode() % container.length);
    }

    /**
     * Tests is there element in this set.
     * @param e element
     * @return true if set contains this element
     */
    public boolean contains(E e) {
        boolean result = false;
        int i = getArrayIndex(e);
        if(container[i] != null) {
            Node<E> x = (Node<E>) container[i];
            if(x.data.equals(e)) {
                result = true;
            } else {
                while(x.next != null) {
                    x = x.next;
                    if(x.data.equals(e)) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Class node for linked list in hash table.
     * @param <E> type of data
     */
    private class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }

}
