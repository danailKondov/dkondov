package ru.job4j.conversion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class converts square arrays to list and back.
 *
 * @since 10/08/2017
 * @version 1
 */
public class ConvertList {

    /**
     * Square array-to-list conversion.
     * @param array - array to convert
     * @return - list as result of conversion
     */
    public List<Integer> toList (int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int i : row) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * Square list-to-array conversion.
     * @param list - list to convert
     * @param rows - number of rows in new square array
     * @return - new square array
     */
    public int[][] toArray (List<Integer> list, int rows) {
        while (list.size()%rows != 0) {
            list.add(0);
        }
        Iterator<Integer> iterator = list.iterator();
        int[][] array = new int[rows][list.size()/rows];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = iterator.next();
            }
        }
        return array;
    }
}
