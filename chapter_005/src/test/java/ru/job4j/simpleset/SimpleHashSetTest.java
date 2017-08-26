package ru.job4j.simpleset;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Class tests simple hash set.
 *
 * @since 26/08/2017
 * @version 1
 */
public class SimpleHashSetTest {

    /**
     * Test add and contains methods.
     */
    @Test
    public void addAndContainsTest() {
        SimpleHashSet<String> set = new SimpleHashSet<>(100);
        for (int i = 0; i < 50; i++) {
            set.add(String.valueOf(Math.random()*1000));
        }
        set.add("Ea"); // все 2 строки имеют один хэш-код и тем самым
        set.add("FB"); // можно протестировать работу контейнера

        assertTrue(set.contains("FB"));
        assertTrue(!set.contains("NoData"));
    }
}