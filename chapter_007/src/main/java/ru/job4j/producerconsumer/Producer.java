package ru.job4j.producerconsumer;

/**
 * Class for simple producer in producer-consumer pattern realization.
 *
 * @since 05/10/2017
 * @version 1
 */
public class Producer implements Runnable {

    private SimpleBlockingQueue<Character> queue;

    public Producer(SimpleBlockingQueue<Character> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        try {
            for (char i = 'A'; i <= 'G'; i++) {
                queue.add(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
