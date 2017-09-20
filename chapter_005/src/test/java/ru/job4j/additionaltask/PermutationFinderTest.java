package ru.job4j.additionaltask;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
}