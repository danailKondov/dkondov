package ru.job4j.additionaltask;

import java.util.Arrays;

/**
 * Class for finding permutations.
 *
 * @since 20/09/2017
 * @version 1
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
}
