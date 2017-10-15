package ru.job4j.bombergame;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is board of the game.
 *
 * @since 15/10/2017
 * @version 1
 */
public class Board {

    /**
     * Game board made of locks.
     */
    private final ReentrantLock[][] board;

    /**
     * Board height.
     */
    private final int HEIGHT;

    /**
     * Board width.
     */
    private final int WIDTH;

    /**
     * Constructor which defines parameters of the game board.
     *
     * @param height is height of the board
     * @param width is width of the board
     */
    public Board(int height, int width) {
        board = new ReentrantLock[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        HEIGHT = height;
        WIDTH = width;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    /**
     * Gets position on the board in form of ReentrantLock.
     *
     * @param height - y
     * @param width - x
     * @return position
     */
    public ReentrantLock getPosition(int height, int width) {
        ReentrantLock result = null;
        if (height < HEIGHT && height >= 0 && width < WIDTH && width >= 0) {
            result = board[height][width];
        }
        return result;
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        Hero hero = new Hero(this);
        Thread mainThread = new Thread(hero);
        mainThread.start();
    }

    public static void main(String[] args) {
        Board board = new Board(10, 10);
        board.startGame();
    }
}
