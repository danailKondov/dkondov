package ru.job4j.wordcounter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for counting words and spaces.
 *
 * @since 17/09/2017
 * @version 1
 */
public class WordCounter {

    /**
     * Text to count.
     */
    private final String source;

    /**
     * Time limit for timer.
     */
    private static final int TIME_LIMIT = 1000;

    /**
     * Working threads.
     */
    private final ArrayList<Thread> threads = new ArrayList<>();

    /**
     * Constructor.
     * @param source is text to count
     */
    public WordCounter(String source) {
        this.source = source;
    }

    /**
     * Counts both words and spaces.
     */
    public void execute() {
        System.out.println("Program begins counting...");
        countWords();
        countSpaces();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Program ends.");
    }

    /**
     * Counts words.
     */
    public void countWords() {
        // counter thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();

                int counter = 0;
                boolean word = false;
                for (int i = 0; i < source.length(); i++) {
                    if (source.charAt(i) != ' ' && !word) {
                        word = true;
                        counter++;
                    } else if(source.charAt(i) == ' ') {
                        word = false;
                    }

                    // interrupting for time-out
                    if(currentThread.isInterrupted()) {
                        System.out.println("Counting words thread was interrupted for time out!");
                        return;
                    }
                }
                System.out.println("words: " + counter);
            }
        });
        threads.add(thread);

        Timer timer = new Timer(true);
        timer.schedule(new TimerForThread(thread), TIME_LIMIT);

        thread.start();
    }

    /**
     * Counts spaces.
     */
    public void countSpaces() {
        // counter thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();

                int counter = 0;
                int fromIndex = 0;
                while(!currentThread.isInterrupted() && (fromIndex = source.indexOf(" ", fromIndex)) >= 0) {
                    counter++;
                    fromIndex++;
                }
                if(currentThread.isInterrupted()) {
                    System.out.println("Counting spaces thread was interrupted for time out!");
                 } else {
                    System.out.println("spaces: " + counter);
                }

            }
        });
        threads.add(thread);

        Timer timer = new Timer(true);
        timer.schedule(new TimerForThread(thread), TIME_LIMIT);

        thread.start();
    }
}
