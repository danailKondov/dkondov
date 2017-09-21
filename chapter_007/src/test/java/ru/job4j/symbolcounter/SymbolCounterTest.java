package ru.job4j.symbolcounter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class tests counting symbols.
 *
 * @since 21/09/2017
 * @version 1
 */
public class SymbolCounterTest {

    /**
     * Tests counter.
     */
    @Test
    public void CounterTest() {
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
        counter.count(sb.toString(), 15);

        // System.out не работает
    }


}