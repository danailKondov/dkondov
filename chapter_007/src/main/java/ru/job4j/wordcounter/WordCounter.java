package ru.job4j.wordcounter;

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
     * Constructor.
     * @param source is text to count
     */
    public WordCounter(String source) {
        this.source = source;
    }

    /**
     * Counts words.
     */
    public void countWords() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String newSource = source.trim();
                String[] words = newSource.split(" ");
                System.out.println("words: " + words.length);
            }
        });
        thread.start();
    }

    /**
     * Counts spaces.
     */
    public void countSpaces() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                int fromIndex = 0;
                while((fromIndex = source.indexOf(" ", fromIndex)) >= 0) {
                    counter++;
                    fromIndex++;
                }
                System.out.println("spaces: " + counter);
            }
        });
        thread.start();
    }
}
