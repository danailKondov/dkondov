package ru.job4j.tracker;

import org.slf4j.Logger;

/**
 * Class for utilities.
 * @since 13/11/2017
 * @version 1
 */
public class Utils {

    /**
     * Closes resources properly.
     * @param closeable resource to close
     * @param log logger
     */
    public static void closeQuietly(AutoCloseable closeable, Logger log) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
