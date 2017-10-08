package ru.job4j.producerconsumer;

import static java.lang.Thread.currentThread;

/**
 * Class for simple consumer in producer-consumer pattern realization.
 *
 * @since 05/10/2017
 * @version 1
 */
public class Consumer implements Runnable {

    private SimpleBlockingQueue<Character> queue;

    public Consumer(SimpleBlockingQueue<Character> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Character c;
        try {
            while ((c = queue.take()) < 'G') {
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
