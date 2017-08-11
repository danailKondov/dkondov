package ru.job4j.conversion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing array to list and back conversion.
 * @since 10/08/2017
 * @version 1
 **/
public class ConvertListTest {

    /**
     * Test for list-to-array conversion.
     */
    @Test
    public void toListConversionTest() {
        ConvertList convertList = new ConvertList();
        int[][]source = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> result = convertList.toList(source);
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9]";
        assertThat(result.toString(), is(expected));
    }

    /**
     * Test for array-to-list conversion.
     */
    @Test
    public void toArrayConversionTest() {
        ConvertList convertList = new ConvertList();
        List<Integer> source = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            source.add(i);
        }
        int[][] result = convertList.toArray(source, 3);
        int[][] expected = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(result, is(expected));
    }

    /**
     * Test for list of arrays conversion to list of integers.
     */
    @Test
    public void convertToListOfIntegersListOfArraysTest() {
        ConvertList convertList = new ConvertList();
        List<int[]> source = new ArrayList<>();
        source.add(new int[]{1, 2});
        source.add(new int[]{3, 4, 5, 6});
        List<Integer> result = convertList.convert(source);
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            expected.add(i);
        }
        assertThat(result, is(expected));
    }

}