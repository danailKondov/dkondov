package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

/**
 * Bishop figure.
 * @since 05/08/2017
 * @version 1
 */
public class Bishop extends Figure  {

    /**
     * Constructor with parameters.
     * @param position - Cell class, reflects position of the figure
     * @param color - color of the bishop
     */
    public Bishop(Cell position, Color color, ChessType type, Board board) {
        super(position, color, type, board);
    }

    /**
     * Method returns bishop's way to the endpoint and throws exception
     * if this way is impossible.
     * @param dist - Cell class, reflects endpoint of the bishop's way
     * @return - array of cells, passed by the bishop on the way to the endpoint
     * @throws ImpossibleMoveException - thrown if way is impossible
     */
    @Override
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        int distX = dist.getX();
        int distY = dist.getY();
        int[][] orient = {{-1, -1},
                          {1, -1},
                          {-1, 1},
                          {1, 1}};
        boolean exist = false;
        int count = 0;
        int sector = 0;
        begin:
        for (int k = 0; k < orient.length; k++) {
            int i = position.getY();
            int j = position.getX();
            count = 0;
            while ((i < Board.HEIGHT && i >= 0) && (j < Board.WIDTH && j >= 0)) {

                if (i == distY && j == distX) {
                    exist = true;
                    sector = k;
                    break begin;
                }
                i = i + orient[k][0];
                j = j + orient[k][1];
                count++;
            }
        }
        Cell[] result = null;
        if (exist) {
            result = new Cell[count];
            for (int i = 0; i < count; i++) {
                result[i] = new Cell(position.getX() + (i + 1)*orient[sector][1], position.getY() + (i + 1)*orient[sector][0]);
            }
        } else {
            throw new ImpossibleMoveException("Move is impossible!");
        }

        return result;
    }

    /**
     * Method clones current bishop with new position.
     * @param dist - new position
     * @return bishop with new position
     */
    @Override
    public Bishop clone(Cell dist) {
        return new Bishop(dist, color, type, board);
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
    @Override
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        Figure figureToMove = board.getFigure(source);
        Cell[] way = figureToMove.way(dist); // throws ImpossibleMoveException if move is forbidden

        // checking is way is free
        Figure[] figures = board.getFigures();
        for (int i = 0; i < way.length ; i++) {
            for (int j = 0; j < figures.length; j++) {
                if(figures[j] != null && way[i].equals(figures[j].position)) {
                    throw new OccupiedWayException("The way is occupied!");
                }
            }
            if (i == way.length - 1) {
                board.removeFigure(figureToMove.position);
                figureToMove = figureToMove.clone(dist);
                board.setFigure(figureToMove);
                result = true;
            }
        }
        return result;
    }

    /**
     * Method computes hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = position.getX();
        result = 31 * result + position.getY() + type.hashCode() + color.hashCode();
        return result;
    }

    /**
     * Method needed for proper comparison of objects.
     * @param obj - figure to compare
     * @return result of comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Bishop otherBishop = (Bishop) obj;

        if (type != otherBishop.type) return false;
        if (color != otherBishop.color) return false;
        return position.equals(otherBishop.position);
    }

}
