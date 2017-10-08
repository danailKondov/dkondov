package ru.job4j.producerconsumer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class for simple producer-consumer pattern realization.
 *
 * @since 05/10/2017
 * @version 1
 */
public class SimpleBlockingQueueTest {

    @Test
    public void producerConsumerTest() {
        SimpleBlockingQueue<Character> queue = new SimpleBlockingQueue<>();
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));
        producerThread.start();
        consumerThread.start();
    }

}