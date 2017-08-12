package ru.job4j.comparators;

import java.util.*;

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

    /**
     * Sorting users with comparator according to
     * name length.
     * @param source - list to sort
     * @return - sorted list
     */
    public List<User> sortNameLength (List<User> source) {
        ArrayList<User> result = new ArrayList<>(source);
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        Collections.sort(result, comparator);
        return result;
    }

    /**
     * Sorting users with comparator according to
     * name and age.
     * @param source - list to sort
     * @return - sorted list
     */
    public List<User> sortByAllFields (List<User> source) {
        ArrayList<User> result = new ArrayList<>(source);
        Comparator<User> comparator = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int res = o1.getName().compareTo(o2.getName());
                if (res == 0) {
                    res = o1.getAge() - o2.getAge();
                }
                return res;
            }
        };
        Collections.sort(result, comparator);
        return result;
    }
}
