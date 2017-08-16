package ru.job4j.iteratorofiterators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing iterator of iterators.
 * @since 16/08/2017
 * @version 1
 **/
public class SuperIteratorConverterTest {

    /**
     * Test for iterator of iterators.
     */
    @Test
    public void superIteratorConverterTest() {
        Iterator<Integer> firstIterator = Arrays.asList(4, 2, 0, 4, 6, 4, 9).iterator();
        Iterator<Integer> secondIterator = Arrays.asList(0, 9, 8, 7, 5).iterator();
        Iterator<Integer> thirdIterator = Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4).iterator();
        Iterator<Iterator<Integer>> it = Arrays.asList(firstIterator, secondIterator, thirdIterator).iterator();
        ConvertIterator convertIterator = new SuperIteratorConverter();
        Iterator<Integer> res = convertIterator.convert(it);
        StringBuilder result = new StringBuilder();
        while(res.hasNext()) {
            result.append(res.next());
        }
        String expected = "420464909875135670984";
        assertThat(result.toString(), is(expected));
    }
}