package ru.job4j.tracker;

/**
 * Exception for menu.
 * @since 04/08/2017
 * @version 1
 **/
public class MenuOutException extends RuntimeException {

    /**
     * Default constructor with message.
     * @param message - message about exception
     */
    public MenuOutException(String message) {
        super(message);
    }
}
