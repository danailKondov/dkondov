package ru.job4j.cyclicityfinder;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests list with cycle.
 *
 * @since 23/08/2017
 * @version 1
 */
public class CycledLinkedListTest {

    /**
     * Test for cyclicity test method.
     */
    @Test
    public void cyclicityTest(){
        CycledLinkedList<Integer> cycledLinkedList = new CycledLinkedList<Integer>();

        Node<Integer> first = new Node(1);
        Node<Integer> two = new Node(2);
        Node<Integer> third = new Node(3);
        Node<Integer> four = new Node(4);

        cycledLinkedList.start = first;
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        assertThat(cycledLinkedList.hasCycle(first), is(true));
    }

}