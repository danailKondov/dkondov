package ru.job4j.conversion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing list-to-map conversion.
 * @since 11/08/2017
 * @version 1
 **/
public class UserConvertTest {

    /**
     * List-to-map conversion test.
     */
    @Test
    public void processListToMapConversion() {
        UserConvert userConvert = new UserConvert();
        List<User> source = new ArrayList<>();
        source.add(new User("Fransois", "Paris"));
        source.add(new User("Angela", "Berlin"));
        source.add(new User("Vladimir", "Moscow"));
        source.add(new User("Donald", "Washington"));
        HashMap<Integer, User> result = userConvert.process(source);
        List<User> res = new ArrayList<>(result.values());
        assertThat(result.keySet().toArray(), is (new int[] {0, 1, 2, 3}));
        assertThat(res, is(source));
    }

}