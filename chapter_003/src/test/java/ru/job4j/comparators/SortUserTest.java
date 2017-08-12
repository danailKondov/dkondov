package ru.job4j.comparators;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing sort with comparable interface.
 * @since 12/08/2017
 * @version 1
 **/
public class SortUserTest {

    /**
     * User sort according to age test.
     */
    @Test
    public void userSortWithComparableTest() {
        SortUser sortUser = new SortUser();
        List<User> source = new ArrayList<>();
        source.add(new User("Pavel", 45));
        source.add(new User("Maria", 15));
        source.add(new User("Magdalena", 30));
        source.add(new User("Andrey", 25));
        source.add(new User("Foma", 27));
        Set<User> result = sortUser.sort(source);
        String expected = "[15, 25, 27, 30, 45]";
        assertThat(result.toString(), is(expected));
    }
}