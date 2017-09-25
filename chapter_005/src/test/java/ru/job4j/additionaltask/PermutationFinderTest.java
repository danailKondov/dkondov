package ru.job4j.additionaltask;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests permutation finder.
 *
 * @since 20/09/2017
 * @version 1
 */
public class PermutationFinderTest {

    /**
     * Tests when first word is permutation of second.
     */
    @Test
    public void whenOneIsPermutationOfSecondTest() {
        PermutationFinder finder = new PermutationFinder();
        boolean result = finder.find("abcde", "cdabe");
        assertTrue(result);
    }

    /**
     * Tests when first word is not permutation of second.
     */
    @Test
    public void whenOneIsNotPermutationOfSecondTest() {
        PermutationFinder finder = new PermutationFinder();
        boolean result = finder.find("abcdf", "cdabe");
        assertFalse(result);
    }

    /**
     * Tests when first word is permutation of second.
     * Realized on HashMap.
     */
    @Test
    public void whenFirstIsPermOfSecondWithHashMapTest() {
        PermutationFinder finder = new PermutationFinder();
        boolean result = finder.findWithHashMap("abcdee", "cdabee");
        assertTrue(result);
    }

    /**
     * Tests when first word is permutation of second.
     * Realized on HashMap.
     */
    @Test
    public void whenFirstIsNotPermOfSecondWithHashMapTest() {
        PermutationFinder finder = new PermutationFinder();
        boolean result = finder.findWithHashMap("abcdee", "cdabeb");
        assertFalse(result);
    }

    /**
     * Tests speed of execution.
     */
    @Test
    public void speedTest() {
        PermutationFinder finder = new PermutationFinder();
        long first = System.nanoTime();
        finder.find("abcde", "cdabe");
        long second = System.nanoTime();
        System.out.println("No-hashmap test time: " + (second - first)/1000000.0 + " ms");

        long firstH = System.nanoTime();
        finder.findWithHashMap("abcde", "cdabe");
        long secondH = System.nanoTime();
        System.out.println("With-hashmap test time: " + (secondH - firstH)/1000000.0 + " ms");

        assertTrue((second - first) < (secondH - firstH));
    }
}