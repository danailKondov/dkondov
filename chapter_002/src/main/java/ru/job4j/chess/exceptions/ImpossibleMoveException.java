package ru.job4j.chess.exceptions;

/**
 * Exception related to the rules of movement.
 * @since 05/08/2017
 * @version 1
 */
public class ImpossibleMoveException extends Exception{

    /**
     * Default constructor with message.
     * @param message - message about exception
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
