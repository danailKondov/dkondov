package ru.job4j.additionaltask;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for finding permutations.
 *
 * @since 25/09/2017
 * @version 2
 */
public class PermutationFinder {

    /**
     * Finds if one word is permutation of other.
     *
     * @param wordOne first word
     * @param wordTwo second word
     * @return true if first word is permutation of second word.
     */
    public boolean find(String wordOne, String wordTwo) {
        char[] one = wordOne.toCharArray();
        char[] two = wordTwo.toCharArray();
        Arrays.sort(one); // O(N*Log(N))
        Arrays.sort(two); // O(N*Log(N))
        String newOne = new String(one);
        String newTwo = new String(two);
        return newOne.equals(newTwo);
    }

    /**
     * Finds if one word is permutation of other. Realized with
     * HashMap.
     *
     * @param wordOne first word
     * @param wordTwo second word
     * @return true if first word is permutation of second word.
     */
    public boolean findWithHashMap(String wordOne, String wordTwo) {
        if (wordOne.length() != wordTwo.length()) return false;

        char[] one = wordOne.toCharArray();
        char[] two = wordTwo.toCharArray();

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        // В map храним в ключах символы и в значениях их количество
        for (int i = 0; i < one.length; i++) {
            Character ch = one[i];
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        // ...вычитаем из суммы символов (values) единицу если такой символ
        // есть во втором слове. Если сумма равна 0, то убираем символ. Если
        // символа из второго слова нет, то прекращаем сравнение и возвращаем false
        for (int i = 0; i < two.length; i++) {
            Character ch = two[i];
            if(map.containsKey(ch)) {
                int sum = 0;
                if((sum = map.get(ch) - 1) > 0) {
                    map.put(ch, sum);
                } else {
                    map.remove(ch);
                }
            } else {
                return false;
            }
        }

        // ...если символов не осталось - значит второе слово является
        // перестановкой первого
        return map.size() == 0;
    }

    public boolean is(String origin, String combine) {
        boolean rsl = false;
        if (origin.length() == combine.length()) {
            rsl = true;
            Map<Character, Integer> data = new HashMap<>();
            for (char value : origin.toCharArray()) {
                data.put(value, data.containsKey(value) ? data.get(value) + 1 : 1);
            }
            for (char value : combine.toCharArray()) {
                Integer count = data.get(value);
                if (count == null || count < 1) {
                    rsl = false;
                    break;
                }
                data.put(value, --count);
            }
        }
        return rsl;
    }
}
