package ru.job4j.simpledynamiclist;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple array list.
 *
 * @since 21/08/2017
 * @version 1
 */
public class SimpleArrayListTest {

    /**
     * Test add and get method.
     */
    @Test
    public void addAndGetTest() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("one");
        simpleArrayList.add("two");
        simpleArrayList.add("three");
        simpleArrayList.add("four");
        String result = simpleArrayList.get(2);
        String expected = "three";
        assertThat(result, is(expected));
    }

    /**
     * Test for iterator.
     */
    @Test
    public void iteratorTest() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("one");
        simpleArrayList.add("two");
        simpleArrayList.add("three");
        simpleArrayList.add("four");
        String result = "";
        for (String s : simpleArrayList) {
            result += s + " ";
        }
        String expected = "one two three four ";
        assertThat(result, is(expected));
    }

}