package ru.job4j.xmloptimisation;

import java.sql.SQLException;

/**
 * Class for utilities.
 * @since 20/11/2017
 * @version 1
 */
public class Util {

    /**
     * Closes resources properly.
     * @param closeable resource to close
     */
    public static void closeQuietly(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
