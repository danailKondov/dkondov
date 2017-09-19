package ru.job4j.wordcounter;

/**
 * Class is timer for counter.
 *
 * @since 19/09/2017
 * @version 1
 */
public class CounterTimer implements Runnable {

    /**
     * Thread to interrupt when time out.
     */
    private Thread thread;

    /**
     * Timer value.
     */
    private int timeInMs;

    /**
     * Constructor.
     *
     * @param thread to control
     * @param timeInMs time limit in ms
     */
    public CounterTimer(Thread thread, int timeInMs) {
        this.thread = thread;
        this.timeInMs = timeInMs;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (thread.isAlive()) {
            thread.interrupt();
        }
    }
}
