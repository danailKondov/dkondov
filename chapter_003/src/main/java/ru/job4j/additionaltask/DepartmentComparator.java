package ru.job4j.additionaltask;

import java.util.Comparator;

/**
 * Class comparator for ascending sort.
 *
 * @since 14/08/2017
 * @version 1
 */
public class DepartmentComparator implements Comparator<String> {

    /**
     * Subtraction.
     *
     * @param one first parameter
     * @param two second parameter
     * @return result of subtraction
     */
    protected int subtraction(int one, int two) {
        // единственная строчка, где надо поменять местами аргументы,
        // чтобы добиться нужного нам результата чравнения
        return one - two;
    }

    /**
     * Compare.
     *
     * @param obj1 - object to compare
     * @param obj2 - object to compare
     * @return result of comparison
     */
    @Override
    public int compare(String obj1, String obj2) {
        int result = 0;
        int i = 1;
        int j = 2;
        int count = 4;
        // в принципе, цикл позволяет упорядочивать названия департаментов
        // любой длинны (до тех пор, пока не меняется логика наименования)
        // - нужно лишь знать изначально их количество, чтобы
        // сказать циклу вовремя остановиться (последний if - либо добавить
        // поиск количества названий департаментов)
        while(result == 0) {
            result = subtraction(obj1.charAt(i), obj2.charAt(i));
            if(result == 0) {
                if (obj1.length() == j) {
                    result = -1;
                } else if (obj2.length() == j) {
                    result = 1;
                }
            }
            i += count;
            j += count;
            count++;
            if (i > 10) break;
        }
        return result;
    }
}
