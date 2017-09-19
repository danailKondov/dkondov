package ru.job4j.wordcounter;

import org.junit.Test;

/**
 * Class tests counting words and spaces.
 *
 * @since 17/09/2017
 * @version 1
 */
public class WordCounterTest {

    /**
     * Tests words and spaces counting.
     */
    @Test
    public void wordsAndSpacesCountTest() {
        String source = "Dies irae, Dies illa!\n" +
                " Solvet saeclum in favilla,\n" +
                " Teste David cum Sybilla.\n" +
                " Quantus tremor est futurus,\n" +
                " Quanto Judex est venturus\n" +
                " Cuncta stricte discussurus.\n";

        WordCounter counter = new WordCounter(source);
        counter.execute();
    }

}