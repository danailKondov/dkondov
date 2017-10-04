package ru.job4j.textsearcher;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests parallel search of words.
 *
 * @since 03/10/2017
 * @version 1
 */
public class ParallelSearchTest {

    /**
     * Tests parallel search.
     */
    @Test
    public void wordSearchTest() {
        List<String> exts = Arrays.asList("txt");
        ParallelSearch search = new ParallelSearch("I:\\test01", "test order of symbols", exts);
        search.go();
        String result = search.result().toString();
        System.out.println(result);
        assertThat(result, is ("[I:\\test01\\test03\\test04\\test18.txt]"));
    }

}