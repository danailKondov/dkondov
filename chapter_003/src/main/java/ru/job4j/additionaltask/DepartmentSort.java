package ru.job4j.additionaltask;

import java.util.*;

/**
 * Class for sort department names.
 *
 * @since 12/08/2017
 * @version 1
 */
public class DepartmentSort {

    /**
     * Sorting in ascending order.
     * @param source - array to sort
     * @return sorted array
     */
    public String[] ascendingSort(String[] source) {
        source = checkAndAddCodes(source);
        Comparator<String> comparator = new DepartmentComparator();
        Arrays.sort(source, comparator);
        return source;
    }

    /**
     * Sorting in descending order.
     * @param source - array to sort
     * @return sorted array
     */
    public String[] descendingSort(String[] source) {
        source = checkAndAddCodes(source);
        Comparator<String> comparator = new DepartmentComparatorDes();
        Arrays.sort(source, comparator);
        return source;
    }

    /**
     * Check array of departments and add missing ones.
     * @param source - array to check
     * @return - checked array
     */
    private String[] checkAndAddCodes(String[] source){
        // хотя по условию у нас массив подразделений, списки и множества обладают нужной
        // функциональностью, поэтому преобразуем массив в список, а потом список
        // обратно в массив.
        ArrayList<String> list = new ArrayList<>(Arrays.asList(source));
        HashSet<String> listToAdd = new HashSet<>();
        for (String depCode : list) {
            if(depCode.contains("\\")) {
                String depCodeToAdd = depCode.substring(0, depCode.lastIndexOf("\\"));
                if(!(list.contains(depCodeToAdd))) listToAdd.add(depCodeToAdd);
            }
        }
        list.addAll(listToAdd);
        return list.toArray(source);
    }
}
