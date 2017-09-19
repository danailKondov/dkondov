package ru.job4j.wordcounter;

import java.util.TimerTask;

/**
 * Class is task for timer.
 *
 * @since 19/09/2017
 * @version 1
 */
public class TimerForThread extends TimerTask {

    /**
     * Thread to stop by timer.
     */
    private Thread thread;

    /**
     * Constructor.
     *
     * @param thread to stop by timer
     */
    public TimerForThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        thread.interrupt();
    }
}
