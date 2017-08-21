package ru.job4j.simplelinkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple linked list.
 *
 * @since 21/08/2017
 * @version 1
 */
public class SimpleLinkedListTest {

    /**
     * Test add and get method.
     */
    @Test
    public void addAndGetTest() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add("one");
        simpleLinkedList.add("two");
        simpleLinkedList.add("three");
        simpleLinkedList.add("four");
        String result = simpleLinkedList.get(3);
        String expected = "four";
        assertThat(result, is(expected));
    }

    /**
     * Test for iterator.
     */
    @Test
    public void iteratorTest() {
        SimpleLinkedList<String> simpleLinkedList = new SimpleLinkedList<>();
        simpleLinkedList.add("one");
        simpleLinkedList.add("two");
        simpleLinkedList.add("three");
        simpleLinkedList.add("four");
        String result = "";
        for (String s : simpleLinkedList) {
            result += s + " ";
        }
        String expected = "one two three four ";
        assertThat(result, is(expected));
    }

}