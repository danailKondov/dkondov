package ru.job4j.additionaltask;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing array to list and back conversion.
 * @since 10/08/2017
 * @version 1
 **/
public class DepartmentSortTest {

    @Test
    public void ascendingSortTest() {
        DepartmentSort departmentSort = new DepartmentSort();
        String[] departmentCodes = {"K2",
                "K1\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K1\\SK2",
                "K1\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1\\SK1"};
        String result = Arrays.toString(departmentSort.ascendingSort(departmentCodes));
        String expected = "[K1, K1\\SK1, K1\\SK1\\SSK1, K1\\SK1\\SSK2, K1\\SK2, K2, K2\\SK1, K2\\SK1\\SSK1, K2\\SK1\\SSK2]";
        assertThat(result, is(expected));
    }

    @Test
    public void descendingSortTest() {
        DepartmentSort departmentSort = new DepartmentSort();
        String[] departmentCodes = {"K2",
                "K1\\SK1\\SSK1",
                "K2\\SK1\\SSK2",
                "K1\\SK2",
                "K1\\SK1\\SSK2",
                "K2\\SK1\\SSK1",
                "K1\\SK1"};
        String result = Arrays.toString(departmentSort.descendingSort(departmentCodes));
        String expected = "[K2, K2\\SK1, K2\\SK1\\SSK2, K2\\SK1\\SSK1, K1, K1\\SK2, K1\\SK1, K1\\SK1\\SSK2, K1\\SK1\\SSK1]";
        assertThat(result, is(expected));
    }

}