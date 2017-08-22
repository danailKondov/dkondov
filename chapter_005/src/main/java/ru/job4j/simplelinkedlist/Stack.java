package ru.job4j.simplelinkedlist;

import java.util.EmptyStackException;

/**
 * Class simple stack.
 *
 * @since 22/08/2017
 * @version 1
 */
public class Stack<E> {

    /**
     * Container for stack.
     */
    private SimpleLinkedList<E> container = new SimpleLinkedList<E>();

    /**
     * Add new element.
     * @param e new element
     */
    public void push(E e) {
        container.add(e);
    }

    /**
     * Get element from the top according to LIFO-logic and remove it.
     * @return element
     * @throws EmptyStackException if stack is empty
     */
    public E pop() {
        if (container.size() == 0) {
            throw new EmptyStackException();
        }
        return container.remove();
    }

    /**
     * Looks at the object at the top of this stack without removing it.
     * @return element
     * @throws EmptyStackException if stack is empty
     */
    public E peek() {
        if (container.size() == 0) {
            throw new EmptyStackException();
        }
        return container.get(container.size() - 1);
    }

    /**
     * Tests is this stack is empty.
     * @return result of test
     */
    public boolean empty() {
        return container.size() == 0;
    }
}
