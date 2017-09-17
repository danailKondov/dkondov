package ru.job4j.wordcounter;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        String source = "Humana ante oculos foede cum vita iaceret";

        WordCounter counter = new WordCounter(source);
        counter.countSpaces();
        counter.countWords();

    }

}