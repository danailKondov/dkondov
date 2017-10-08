package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for simple producer-consumer pattern realization.
 *
 * @since 04/10/2017
 * @version 1
 */
@ThreadSafe
public class SimpleBlockingQueue<E> {

    /**
     * Simple queue with size = 1.
     */
    @GuardedBy("lock")
    private Object[] queue = new Object[1];

    /**
     * Variable is true if queue is full.
     */
    @GuardedBy("lock")
    private boolean isFull = false;

    /**
     * Lock.
     */
    Lock lock = new ReentrantLock();

    /**
     * Condition when queue is empty.
     */
    Condition whenIsEmpty = lock.newCondition();

    /**
     * Condition when queue is full.
     */
    Condition whenIsFull = lock.newCondition();

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     *
     * @param e element to add
     */
    public void add(E e) {
        lock.lock();
        try {
            while(isFull) {
                whenIsFull.await();
            }
            queue[0] = e;
            System.out.println("Thread " + Thread.currentThread().getId() + "-> " + e);
            isFull = true;
            whenIsEmpty.signal();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves and removes the head of this queue, waiting if
     * necessary until an element becomes available.
     */
    public E take() {
        lock.lock();
        E result = null;
        try {
            while (!isFull) {
                whenIsEmpty.await();
            }
            result = (E) queue[0];
            queue[0] = null;
            System.out.println(result + " -> by Thread " + Thread.currentThread().getId());
            isFull = false;
            whenIsFull.signal();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }

    /**
     * Test method.
     * @param args no use
     */
    public static void main(String[] args) {
        SimpleBlockingQueue<Character> queue = new SimpleBlockingQueue<>();
        Thread producerThread = new Thread(new Producer(queue));
        Thread producerThread2 = new Thread(new Producer(queue));
        Thread producerThread3 = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));
        Thread consumerThread2 = new Thread(new Consumer(queue));
        producerThread.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread.start();
        consumerThread2.start();
    }
}
