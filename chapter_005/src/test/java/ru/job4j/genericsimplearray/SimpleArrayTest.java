package ru.job4j.genericsimplearray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing simple generic.
 *
 * @since 17/08/2017
 * @version 1
 **/
public class SimpleArrayTest {

    /**
     * Test simple array methods.
     */
    @Test
    public void createAddGetUpdateDeleteTest() {
        SimpleArray<String> simpleArray = new SimpleArray<>(10);
        simpleArray.add("test1");
        simpleArray.add("test2");
        simpleArray.add("test3");
        simpleArray.add("test4");
        String result = simpleArray.get(0);
        assertThat(result, is("test1"));
        simpleArray.update(1, "test5");
        assertThat(simpleArray.get(1), is("test5"));
        simpleArray.delete(2);
        result = null;
        assertThat(simpleArray.get(2), is(result));
    }

}