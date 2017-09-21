package ru.job4j.symbolcounter;

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
        try {
            long start = System.nanoTime();
            Thread.sleep(timeout);
            long end = System.nanoTime();

            if (threadToStop.isAlive()) {
                threadToStop.interrupt();
                System.out.println("Time limit is out!");
            }

            double result = (end-start)/1000000.0;
            System.out.println("timer worked in ms: " + result);
            System.out.println("Timeout was set in ms:  " + timeout);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
