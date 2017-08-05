package ru.job4j.chess.exceptions;

/**
 * Exception related to the existence of figure.
 * @since 05/08/2017
 * @version 1
 */
public class FigureNotFoundException extends Exception {

    /**
     * Default constructor with message.
     * @param message - message about exception
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
