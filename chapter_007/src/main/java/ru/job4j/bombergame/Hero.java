package ru.job4j.bombergame;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is hero of the game.
 *
 * @since 15/10/2017
 * @version 1
 */
public class Hero extends Character {

    public Hero(Board board, int x, int y, String name) {
        super(board, x, y, name);
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
}
