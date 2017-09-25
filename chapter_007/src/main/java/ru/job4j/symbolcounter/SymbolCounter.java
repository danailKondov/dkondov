package ru.job4j.symbolcounter;

/**
 * Class for counting symbols with timeout.
 *
 * @since 20/09/2017
 * @version 1
 */
public class SymbolCounter {

    private static final int TIMEOUT = 16;

    /**
     * Counts symbols in source string with limited time.
     * @param source text
     * @param timeout time limit
     */
    public void count(String source, int timeout) {
        Thread thread = new Thread(new CountChar(source, timeout));
        thread.start();
    }

    public static void main(String[] args) {
        SymbolCounter counter = new SymbolCounter();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("Efforts by Madrid to stop a Catalonia independence vote," +
                    " currently slated for October 1st, seem to be growing more hostile by the day.  " +
                    "Earlier this week Spanish police seized control of Catalonia’s finances, seeking " +
                    "to ensure that separatist politicians could not spend further public funds on the " +
                    "referendum, and conducted raids across Catalonia to confiscate ballots and campaign " +
                    "materials from printing shops and delivery companies.");
        }
        counter.count(sb.toString(), TIMEOUT);
    }
}
