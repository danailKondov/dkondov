package ru.job4j.simplemap;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests handbook (map).
 *
 * @since 29/08/2017
 * @version 1
 */
public class HandbookTest {

    /**
     * Test for insert method and iterator.
     */
    @Test
    public void insertAndIteratorTest() {
        Handbook<String, String> handbook = new Handbook<>(10);
        handbook.insert("A", "1");
        handbook.insert("B", "2");
        handbook.insert("C", "3");
        String result = "";
        for (String s : handbook) {
            result += s;
        }
        assertThat(result, is("ABC"));
    }

    /**
     * Test for get method.
     */
    @Test
    public void getTest() {
        Handbook<String, String> handbook = new Handbook<>(10);
        handbook.insert("A", "1");
        handbook.insert("B", "2");
        handbook.insert("C", "3");
        assertThat(handbook.get("B"), is ("2"));
    }

    /**
     * Test for delete method.
     */
    @Test
    public void deleteTest() {
        Handbook<String, String> handbook = new Handbook<>(10);
        handbook.insert("A", "1");
        handbook.insert("B", "2");
        handbook.insert("C", "3");
        handbook.delete("C");
        String result = "";
        for (String s : handbook) {
            result += s;
        }
        assertThat(result, is("AB"));
    }
}