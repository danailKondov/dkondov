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
        String expected = "[Maria-15, Andrey-25, Foma-27, Magdalena-30, Pavel-45]";
        assertThat(result.toString(), is(expected));
    }

    /**
     * User sort according to name's length test.
     */
    @Test
    public void userSortAccordingToNameLengthTest() {
        SortUser sortUser = new SortUser();
        List<User> source = new ArrayList<>();
        source.add(new User("Pavel", 45));
        source.add(new User("Maria", 15));
        source.add(new User("Magdalena", 30));
        source.add(new User("Andrey", 25));
        source.add(new User("Foma", 27));
        List<User> result = sortUser.sortNameLength(source);
        String expected = "[Foma-27, Pavel-45, Maria-15, Andrey-25, Magdalena-30]";
        assertThat(result.toString(), is(expected));
    }

    /**
     * User sort according to name and age test.
     */
    @Test
    public void userSortAccToNameAndAgeTest() {
        SortUser sortUser = new SortUser();
        List<User> source = new ArrayList<>();
        source.add(new User("Pavel", 45));
        source.add(new User("Pavel", 20));
        source.add(new User("Maria", 15));
        source.add(new User("Maria", 30));
        source.add(new User("Maria", 70));
        source.add(new User("Magdalena", 30));
        source.add(new User("Andrey", 25));
        source.add(new User("Andrey", 15));
        source.add(new User("Foma", 27));
        List<User> result = sortUser.sortByAllFields(source);
        String expected = "[Andrey-15, Andrey-25, Foma-27, Magdalena-30, Maria-15, Maria-30, Maria-70, Pavel-20, Pavel-45]";
        assertThat(result.toString(), is(expected));
    }
}