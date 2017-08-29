package ru.job4j.simplelinkedlist;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple queue.
 *
 * @since 22/08/2017
 * @version 1
 */
public class QueueTest {

    /**
     * Test add, poll and remove methods.
     */
    @Test
    public void addRemoveAndPollTest() {
        Queue<String> queue = new Queue<>();
        queue.add("One");
        queue.add("Two");
        queue.add("Three");
        queue.add("Four");
        queue.add("Five");
        queue.add("Six");
        String result = queue.element();
        assertThat(result, is("One"));
        result = "";
        while(!queue.isEmpty()) {
            result += queue.poll();
        }
        assertThat(result, is("OneTwoThreeFourFiveSix"));
    }

}