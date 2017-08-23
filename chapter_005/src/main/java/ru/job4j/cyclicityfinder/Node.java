package ru.job4j.cyclicityfinder;

/**
 * Class simple node of linked list.
 *
 * @since 23/08/2017
 * @version 1
 */
public class Node<T> {

    /**
     * Value.
     */
    private T value;

    /**
     * Next node.
     */
    public Node<T> next;

    /**
     * Constructor.
     * @param value value
     */
    public Node(T value) {
        this.value = value;
    }
}
