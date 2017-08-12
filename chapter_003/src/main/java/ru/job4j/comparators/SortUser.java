package ru.job4j.comparators;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class simple sort of users.
 *
 * @since 12/08/2017
 * @version 1
 */
public class SortUser {

    /**
     * Sorting users according to age.
     * @param source - list of users
     * @return - sorted set of users
     */
    public Set<User> sort (List<User> source) {
        TreeSet<User> result = new TreeSet<>(source);
        return result;
    }
}
