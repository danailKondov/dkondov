package ru.job4j.bombergame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is hero of the game.
 *
 * @since 15/10/2017
 * @version 1
 */
public class Hero extends Character {

    private volatile Direction direction;

    public Hero(Board board, int y, int x, String name) {
        super(board, y, x, name);

        // направление по умолчанию, пока игрок не сделал ход,
        // вызвав setDirection()
        direction = Direction.RIGHT;
    }

    @Override
    public void run() {
        try {
            // захватываем первую позицию
            position.lock();

            while(!Thread.currentThread().isInterrupted()) {
                boolean result = move(direction);
                if (!result) {
                    continue;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            logger.info("Thread " + name + " was interrupted! ");
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
