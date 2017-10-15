package ru.job4j.bombergame;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is hero of the game.
 *
 * @since 15/10/2017
 * @version 1
 */
public class Hero implements Runnable {

    private final Board board;

    private ReentrantLock position;

    private int x;

    private int y;

    public Hero(Board board) {
        this.board = board;

        // герой начинает в центре доски
        y = board.getHEIGHT()/2;
        x = board.getWIDTH()/2;
        position = board.getPosition(y, x);

    }

    @Override
    public void run() {
        try {
            // захватываем первую позицию
            position.lock();

            while(!Thread.currentThread().isInterrupted()) {

                // выбираем случайное направление и потом, если ход успешный,
                // засыпаем на секунду
                boolean result = move(Direction.values()[(int)(Math.random()*4)]);
                if (!result) {
                    continue;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException ignored) { /*NOP*/ }
    }

    /**
     * Moves hero on the board.
     *
     * @param direction of the move
     * @return true if move was successful
     * @throws InterruptedException if thread was interrupted
     */
    public boolean move(Direction direction) throws InterruptedException {
        boolean result = false;

        if(direction == Direction.DOWN) {
            result = move(++y, x);
            if(!result) {
                y--;
            } else {
                System.out.println("Hero moved down: y = " + y + ", x = " + x);
            }
        } else if (direction == Direction.UP) {
            result = move(--y, x);
            if(!result) {
                y++;
            } else {
                System.out.println("Hero moved up: y = " + y + ", x = " + x);
            }
        } else if (direction == Direction.LEFT) {
            result = move(y, --x);
            if(!result) {
                x++;
            } else {
                System.out.println("Hero moved left: y = " + y + ", x = " + x);
            }
        } else if (direction == Direction.RIGHT) {
            result = move(y, ++x);
            if(!result) {
                x--;
            } else {
                System.out.println("Hero moved right: y = " + y + ", x = " + x);
            }
        }

        return result;
    }

    private boolean move(int y, int x) throws InterruptedException {
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
