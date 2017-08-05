package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

/**
 * Superclass for all figures.
 * @since 05/08/2017
 * @version 1
 */
public abstract class Figure {
    /**
     * Position of the figure.
     */
    protected final Cell position;

    /**
     * Color of the figure.
     */
    protected final Color color;

    /**
     * Status of the figure.
     */
    protected boolean alive;

    /**
     * Game board.
     */
    protected Board board;

    /**
     * Type of figure.
     */
    protected final ChessType type;

    /**
     * Constructor with parameters.
     * @param position - Cell class, reflects position of the figure
     */
    public Figure(Cell position, Color color, ChessType type, Board board) {
        this.position = position;
        this.color = color;
        this.type = type;
        this.board = board;
        this.alive = true;
    }

    /**
     * Method returns figure's way to the endpoint and throws exception
     * if this way is impossible.
     * @param dist - Cell class, reflects endpoint of the figure's way
     * @return - array of cells, passed by the figure on the way to the endpoint
     * @throws ImpossibleMoveException - thrown if way is impossible
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;

    /**
     * Method for cloning figure with new cell.
     * @param dist - new cell
     * @return - cloned figure
     */
    abstract Figure clone(Cell dist);

    /**
     * Moves figure from one position to another.
     * @param source - start position
     * @param dist - end position
     * @return - boolean result of the move
     * @throws ImpossibleMoveException - thrown if way is impossible
     * @throws OccupiedWayException - thrown if way is occupied
     * @throws FigureNotFoundException - thrown if figure to move not found
     */
    abstract boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException;
}
