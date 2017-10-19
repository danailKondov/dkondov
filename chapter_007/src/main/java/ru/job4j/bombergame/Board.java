package ru.job4j.bombergame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.*;

/**
 * Class is board of the game.
 *
 * @since 15/10/2017
 * @version 1
 */
public class Board {

    /**
     * Logger.
     */
    private final Logger logger = LoggerFactory.getLogger("Board");

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
     * Number of monsters in the game.
     */
    private final int NUM_OF_MONSTERS;
    private int mCount = 0;

    /**
     * Pool of monsters.
     */
    private final Runnable[] monsters;

    /**
     * Game's hero.
     */
    private Hero hero;

    /**
     * Thread pool.
     */
    private final ExecutorService pool;

    /**
     * Constructor which defines parameters of the game board.
     *
     * @param height is height of the board
     * @param width is width of the board
     */
    public Board(int height, int width, int numOfMonsters) {
        board = new ReentrantLock[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        HEIGHT = height;
        WIDTH = width;
        NUM_OF_MONSTERS = numOfMonsters;
        monsters = new Runnable[NUM_OF_MONSTERS];
        pool = Executors.newCachedThreadPool();
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
     * Gets hero position.
     *
     * @return position
     */
    public ReentrantLock getHeroPosition() {
        return hero.getPosition();
    }

    /**
     * Enters blocked areas.
     *
     * @param y - y
     * @param x - x
     * @return true if ops was successful
     */
    public boolean enterBlockedArea(int y, int x) {
        boolean result = false;
        if (y < HEIGHT && y >= 0 && x < WIDTH && x >= 0) {
            board[y][x].lock();
            result = true;
            logger.info("Blocked area added: y = " + y + ", x = " + x);
        }
        return result;
    }

    /**
     * Enters monsters.
     *
     * @param y - y
     * @param x - x
     * @param name - name
     * @return true if ops was successful
     */
    public boolean enterMonster(int y, int x, String name) {
        boolean result = false;
        if (y < HEIGHT && y >= 0 && x < WIDTH && x >= 0 && mCount < NUM_OF_MONSTERS) {
            monsters[mCount++] = new Monster(this, y, x, name);
            result = true;
            logger.info("Monster added: y = " + y + ", x = " + x + ", name = " + name);
        }
        return result;
    }

    /**
     * Enters hero.
     *
     * @param y - y
     * @param x - x
     * @param name - name
     * @return true if ops was successful
     */
    public boolean enterHero(int y, int x, String name) {
        boolean result = false;
        if (y < HEIGHT && y >= 0 && x < WIDTH && x >= 0) {
            hero = new Hero(this, y, x, name);
            result = true;
            logger.info("Hero added: y = " + y + ", x = " + x + ", name = " + name);
        }
        return result;
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        pool.submit(hero);
        for (int i = 0; i < NUM_OF_MONSTERS; i++) {
            pool.submit(monsters[i]);
        }
    }

    /**
     * Ends the game.
     */
    public void endGame() {
        System.out.println("Game over!");
        pool.shutdownNow();
    }

    public static void main(String[] args) {
        Board board = new Board(10, 10, 3);
        board.enterBlockedArea(2,2);
        board.enterBlockedArea(3,3);
        board.enterBlockedArea(7,7);
        board.enterBlockedArea(8,8);
        board.enterMonster( 0, 0, "Kashei Bessmertni");
        board.enterMonster(0, 9, "Baba Yaga");
        board.enterMonster(9, 9, "Zmei Gorinich");
        board.enterHero(5,5, "Ilya Muromec");
        board.startGame();
    }
}
