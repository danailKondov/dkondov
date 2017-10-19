package ru.job4j.bombergame;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class is monster in the game.
 *
 * @since 16/10/2017
 * @version 1
 */
public class Monster extends Character {

    public Monster(Board board, int y, int x, String name) {
        super(board, y, x, name);
    }

    @Override
    public void run() {
        try {
            // захватываем первую позицию
            position.lock();

            while(!Thread.currentThread().isInterrupted()) {

                // выбираем случайное направление
                move(Direction.values()[(int)(Math.random()*4)]);

                // скорость чудовищ = один шаг в 0,5 сек.
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            logger.info("Thread " + name + " was interrupted! ");
        }
    }

    @Override
    protected boolean move(int y, int x) throws InterruptedException {
        boolean result = false;
        ReentrantLock oldPosition = position;
        position = board.getPosition(y, x);

        if(position != null) {
            result = position.tryLock();

            // если не удалось получить lock, то...
            if (!result) {

                //...проверяем не была ли занята позиция героем, если была - игра оканчивается:
                if (board.getHeroPosition() == position) {
                    System.out.println(name + " trapped hero!");
                    board.endGame();
                    return result;
                }

                // ...возвращаемся на исходную позицию и спим 5 сек.
                position = oldPosition;
                Thread.sleep(5000);
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
