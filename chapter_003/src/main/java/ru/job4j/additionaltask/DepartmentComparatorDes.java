package ru.job4j.additionaltask;

import java.util.Comparator;

/**
 * Class comparator for descending sort.
 *
 * @since 14/08/2017
 * @version 1
 */
public class DepartmentComparatorDes extends DepartmentComparator {

    /**
     * Subtraction.
     *
     * @param one first parameter
     * @param two second parameter
     * @return result of subtraction
     */
    @Override
    protected int subtraction(int one, int two) {
        return super.subtraction(two, one);
    }
}
