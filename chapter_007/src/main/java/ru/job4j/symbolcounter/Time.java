package ru.job4j.symbolcounter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for timer.
 *
 * @since 20/09/2017
 * @version 1
 */
public class Time implements Runnable {

    /**
     * Thread to stop.
     */
    private Thread threadToStop;

    /**
     * Timeout in milliseconds.
     */
    private int timeout;

    /**
     * Constructor.
     *
     * @param threadToStop thread to stop
     * @param timeout timeout
     */
    public Time(Thread threadToStop, int timeout) {
        this.threadToStop = threadToStop;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                threadToStop.interrupt();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, timeout);
    }
}
