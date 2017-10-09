package ru.job4j.simplethreadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class for simple thread pool.
 *
 * @since 08/10/2017
 * @version 1
 */
public class SimpleThreadPool {

    /**
     * Number of CPU cores.
     */
    private final int CPU_NUM;

    /**
     * Thread pool.
     */
    private final Thread[] threadPool;

    /**
     * Queue of tasks for threads in pool.
     */
    private final BlockingQueue<Runnable> taskQueue;

    /**
     * Constructor.
     *
     * @param capacity of the queue
     */
    public SimpleThreadPool(int capacity) {
        CPU_NUM = Runtime.getRuntime().availableProcessors();
        threadPool = new Thread[CPU_NUM];
        taskQueue = new ArrayBlockingQueue<Runnable>(capacity);
        initialize();
    }

    /**
     * Initialize threads in poll.
     */
    private void initialize() {
        for (int i = 0; i < CPU_NUM; i++) {
            threadPool[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Runnable command = taskQueue.take();
                            command.run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threadPool[i].start();
        }
    }

    /**
     * Adds new task for execution.
     * @param work - new task.
     */
    public void add(Work work) {
        try {
            taskQueue.put(new Runnable() {
                @Override
                public void run() {
                    work.someWork();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test method.
     *
     * @param args no use
     */
    public static void main(String[] args) {
        SimpleThreadPool pool = new SimpleThreadPool(2);
        for (int i = 0; i < 10; i++) {
            pool.add(new Work());
        }
    }
}