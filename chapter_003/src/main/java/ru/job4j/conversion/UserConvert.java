package ru.job4j.conversion;

import java.util.HashMap;
import java.util.List;

/**
 * Class converts list to map.
 *
 * @since 11/08/2017
 * @version 1
 */
public class UserConvert {

    /**
     * List-to-map converter.
     * @param list - list to convert
     * @return - converted map
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap();
        for (User user :
                list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
