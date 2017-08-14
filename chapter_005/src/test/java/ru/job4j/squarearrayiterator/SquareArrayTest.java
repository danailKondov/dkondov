package ru.job4j.squarearrayiterator;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Class for testing square array iterator.
 * @since 14/08/2017
 * @version 1
 **/
public class SquareArrayTest {

    /**
     * Test for square array iterator.
     */
    @Test
    public void squareArrayIteratorTest() {
        int[][] source = {{1, 2}, {3, 4}};
        SquareArray array = new SquareArray(source);
        int[] result = new int[4];
        int j = 0;
        for (int i : array) {
            result[j++] = i;
        }
        int[] expected = {1, 2, 3, 4};
        assertArrayEquals(result, expected);
    }

}