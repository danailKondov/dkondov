package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

/**
 * Main field of the game.
 * @since 05/08/2017
 * @version 1
 */
public class Board {

    /**
     * Height of the board.
     */
    public static final int HEIGHT = 8;

    /**
     * Width of the board.
     */
    public static final int WIDTH = 8;

    /**
     *  Actual figures in game.
     */
    private Figure[] figures;

    /**
     * Method sets figures on board at the start of the game.
     */
    public void setAllFigures() {
        figures = new Figure[32];
        figures[0] = new Bishop(new Cell(2, 0), Color.BLACK, ChessType.BISHOP, this);
        figures[1] = new Bishop(new Cell(5, 0), Color.BLACK, ChessType.BISHOP, this);
        figures[2] = new Bishop(new Cell(2, 7), Color.WHITE, ChessType.BISHOP,this);
        figures[3] = new Bishop(new Cell(5, 7), Color.WHITE, ChessType.BISHOP,this);
    }

    /**
     * Gets figure from array of actual figures.
     * @param source - cell of figure to get
     * @return figure
     */
    public Figure getFigure(Cell source) {
        Figure result = null;
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null && figures[i].position.equals(source)) {
                result = figures[i];
                break;
            }
        }
        return result;
    }

    /**
     * Getter for array of actual figures.
     * @return figures
     */
    public Figure[] getFigures() {
        return figures;
    }

    /**
     * Sets new figure to array of actual figures.
     * @param figure figure to set
     */
    public void setFigure(Figure figure) {
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] == null) {
                figures[i] = figure;
                break;
            }
        }
    }

    /**
     * Removes figures from array of actual figures.
     * @param source - cell of figure to remove
     */
    public void removeFigure(Cell source) {
        for (int i = 0; i < figures.length; i++) {
            if (figures[i] != null && figures[i].position.equals(source)) {
                figures[i] = null;
                break;
            }
        }
    }

    /**
     * Moves figure from one position to another.
     * @param source - start position
     * @param dist - end position
     * @return - boolean result of the move
     * @throws ImpossibleMoveException - thrown if way is impossible
     * @throws OccupiedWayException - thrown if way is occupied
     * @throws FigureNotFoundException - thrown if figure to move not found
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;

        Figure figureToMove = getFigure(source);
        // checking is there is a figure to move
        if (figureToMove == null) throw new FigureNotFoundException("No figure to move!");

        result = figureToMove.move(source, dist);

        return result;
    }
}
