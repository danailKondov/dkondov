package ru.job4j.symbolcounter;

/**
 * Class for symbols counting.
 *
 * @since 20/09/2017
 * @version 1
 */
public class CountChar implements Runnable {

    /**
     * Text to count symbols in.
     */
    private String textToCount;

    /**
     * Timeout in milliseconds.
     */
    private int timeout;

    /**
     * Constructor.
     *
     * @param textToCount text to count
     * @param timeout time limit
     */
    public CountChar(String textToCount, int timeout) {
        this.textToCount = textToCount;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        Thread time = new Thread(new Time(currentThread, timeout));
        time.start();

        int count = 0;
        long start = System.nanoTime();
        for (int i = 0; i < textToCount.length(); i++) {
            if (textToCount.charAt(i) != ' ') {
                count++;
            }
            if (currentThread.isInterrupted()) {
                break;
            }
        }
        if (!currentThread.isInterrupted()) {
            long end = System.nanoTime();
            System.out.println("symbols in text: " + count);
            System.out.println("time to count in ms: " + (end-start)/1000000.0);
        } else {
            long end = System.nanoTime();
            System.out.println("counting was interrupted!");
            System.out.println("time to count in ms: " + (end-start)/1000000.0);
        }
    }
}
