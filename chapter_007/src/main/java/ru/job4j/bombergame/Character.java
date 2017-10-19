package ru.job4j.bombergame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class character hold common traits for all heroes in the game.
 *
 * @since 16/10/2017
 * @version 1
 */
public abstract class Character implements Runnable {

    protected String name;

    protected final Board board;

    protected volatile ReentrantLock position;

    protected int x;

    protected int y;

    protected final Logger logger = LoggerFactory.getLogger("Thread " + name);

    /**
     * Constructor.
     *
     * @param board of the game
     * @param y - y coordinate of initial position
     * @param x - x coordinate of initial position
     * @param name of the character
     */
    public Character(Board board, int y, int x, String name) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.name = name;
        position = board.getPosition(y, x);
    }

    /**
     * Moves hero on the board.
     *
     * @param direction of the move
     * @return true if move was successful
     * @throws InterruptedException if thread was interrupted
     */
    protected boolean move(Direction direction) throws InterruptedException {
        boolean result = false;
        int y1 = y;
        int x1 = x;
        result = move(y1 += direction.getY(), x1 += direction.getX());
        if(result) {
            logger.info("Thread " + name + " moved " + direction.toString().toLowerCase() + ": y = " + y1 + ", x = " + x1);
            System.out.println(name + " moved " + direction.toString().toLowerCase() + ": y = " + y1 + ", x = " + x1);
            y = y1;
            x = x1;
        }
        return result;
    }

    /**
     * Moves hero on the board.
     *
     * @param y - y
     * @param x - x
     * @return true if move was successful
     * @throws InterruptedException if thread was interrupted
     */
    protected boolean move(int y, int x) throws InterruptedException {
        boolean result = false;
        ReentrantLock oldPosition;

        oldPosition = position;
        position = board.getPosition(y, x);

        if(position != null) {
            result = position.tryLock(500, TimeUnit.MILLISECONDS);

            // если не удалось получить lock, то возвращаемся на исходную позицию
            if (!result) {
                position = oldPosition;
            } else {
                // если удалось получить lock, то освобождаем прежнюю позицию
                oldPosition.unlock();
            }
        } else {
            // если позиции не существует (== null), то возвращаемся на исходную позицию
            position = oldPosition;
        }

        return result;
    }

    public ReentrantLock getPosition() {
        return position;
    }
}
