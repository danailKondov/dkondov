package ru.job4j.cyclicityfinder;

/**
 * Class simple linked list with cycle to find.
 *
 * @since 23/08/2017
 * @version 1
 */
public class CycledLinkedList<T> {

    /**
     * First node.
     */
    public Node<T> start;

    /**
     * Tests is there is cycle in list.
     * @param first start node
     * @return true if there is cycle in list
     */
    public boolean hasCycle(Node<T> first) {
        Node<T> tortoise = first;
        Node<T> hare = first;
        boolean result = false;

        while(hare != null || tortoise != null) {
            tortoise = tortoise.next; // черепаха идет с шагом 1 по списку
            hare = hare.next.next; // заяц идет с шагом 2
            if (hare == tortoise) { // если черепаха догнала зайца, значит они движутся по кругу
                result = true;
                break;
            }
        }
        return result;
    }
}
