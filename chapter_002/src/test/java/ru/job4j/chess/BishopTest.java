package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing Bishop class.
 * @since 05/08/2017
 * @version 1
 **/
public class BishopTest {

    /**
     * Method for testing "way" operation when end position
     * is right.
     */
    @Test
    public void wayTestWhenMatch() {
        Board board = new Board();
        Bishop bis = new Bishop(new Cell(3, 3), Color.BLACK, ChessType.BISHOP, board);
        Cell[] result = null;
        try {
            result = bis.way(new Cell(7, 7));
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        }
        Cell[] expected = {new Cell(4,4),
                        new Cell(5,5),
                        new Cell(6,6),
                        new Cell(7,7)};
        assertThat(result, is(expected));
    }

    /**
     * Method for testing "way" operation when end position
     * is wrong.
     */
    @Test
    public void wayTestWhenWrong() {
        Board board = new Board();
        Bishop bis = new Bishop(new Cell(3, 3), Color.BLACK, ChessType.BISHOP, board);
        boolean result = false;
        try {
            bis.way(new Cell(6, 7));
        } catch (ImpossibleMoveException e) {
            result = true;
        }
        assertThat(result, is(true));
    }

    /**
     * Method for testing "move" operation when way is free.
     */
    @Test
    public void moveTestWhenFree() {
        Board board = new Board();
        Bishop bis = new Bishop(new Cell(3, 3), Color.BLACK, ChessType.BISHOP, board);
        board.setAllFigures();
        board.setFigure(bis);
        boolean result2 = false;
        try {
            result2 = board.move(new Cell(3, 3), new Cell(7, 7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Figure expected = new Bishop(new Cell(7,7 ), Color.BLACK, ChessType.BISHOP, board);
        Figure result = board.getFigure(new Cell(7,7));
        assertThat(result, is(expected));
        assertThat(result2, is(true));
    }

    /**
     * Method for testing "move" operation when way is not free.
     */
    @Test
    public void moveTestOccupiedWayException() {
        Board board = new Board();
        Bishop bis = new Bishop(new Cell(3, 3), Color.BLACK, ChessType.BISHOP, board);
        Bishop obstacle = new Bishop(new Cell(7,7 ), Color.BLACK, ChessType.BISHOP, board);
        board.setAllFigures();
        board.setFigure(bis);
        board.setFigure(obstacle);
        boolean result = false;
        try {
            board.move(new Cell(3, 3), new Cell(7, 7));
        } catch (OccupiedWayException e) {
            result = true;
        } catch (Exception e) {}
        assertThat(result, is(true));
    }

    /**
     * Method for testing "move" operation when figure not exists.
     */
    @Test
    public void moveTestFigureNotFoundException() {
        Board board = new Board();
        board.setAllFigures();
        boolean result = false;
        try {
            board.move(new Cell(3, 3), new Cell(7, 7));
        } catch (FigureNotFoundException e) {
            result = true;
        } catch (Exception e) {}
        assertThat(result, is(true));
    }

}