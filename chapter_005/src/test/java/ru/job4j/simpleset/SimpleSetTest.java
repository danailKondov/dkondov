package ru.job4j.simpleset;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple set.
 *
 * @since 24/08/2017
 * @version 1
 */
public class SimpleSetTest {

    /**
     * Test add method and iterator.
     */
    @Test
    public void addToSimpleSetAndIteratorTest() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("One");
        simpleSet.add("One");
        simpleSet.add("Two");
        simpleSet.add("Three");
        simpleSet.add("Two");
        simpleSet.add("Three");
        String result = "";
        for (String s : simpleSet) {
            result += s + " ";
        }
        assertThat(result, is("One Two Three "));
    }
}