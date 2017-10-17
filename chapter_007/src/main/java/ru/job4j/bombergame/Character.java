package ru.job4j.bombergame;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class character hold common traits for all heroes in the game.
 *
 * @since 16/10/2017
 * @version 1
 */
public abstract class Character implements Runnable {

    private String name;

    private final Board board;

    protected ReentrantLock position;

    protected int x;

    protected int y;

    public Character(Board board, int x, int y, String name) {
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
            System.out.println(name + " moved " + direction.toString().toLowerCase() + ": y = " + y1 + ", x = " + x1);
            y = y1;
            x = x1;
        }
        return result;
    }

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
}
