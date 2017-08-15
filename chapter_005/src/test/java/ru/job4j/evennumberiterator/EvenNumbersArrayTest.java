package ru.job4j.evennumberiterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing even numbers iterator.
 * @since 15/08/2017
 * @version 1
 **/
public class EvenNumbersArrayTest {

    /**
     * Test for even numbers iterator.
     */
    @Test
    public void evenNubersIteratorTest() {
        EvenNumbersArray evenNumbersArray = new EvenNumbersArray(new int[] {66, 446, 4, 2, 1, 1});
        String result = "";
        for (int i : evenNumbersArray) {
            result += i;
        }
        String expected = "6644642";
        assertThat(result, is (expected));
    }

}