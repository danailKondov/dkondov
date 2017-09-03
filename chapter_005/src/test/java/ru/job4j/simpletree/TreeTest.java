package ru.job4j.simpletree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple tree.
 *
 * @since 03/09/2017
 * @version 1
 */
public class TreeTest {

    @Test
    public void addAndIteratorTest() {
        SimpleTree<String> simpleTree = new Tree<>();
        simpleTree.add("One", "Two");
        simpleTree.add("Two", "Three");
        simpleTree.add("One", "Three");
        simpleTree.add("Two", "Four");
        simpleTree.add("Two", "One");
        simpleTree.add("Two", "Five");
        simpleTree.add("Two", "Three");
        simpleTree.add("Three", "Six");
        simpleTree.add("Three", "Five");
        simpleTree.add("Three", "Six");
        simpleTree.add("Three", "Seven");
        simpleTree.add("Three", "Seven");
        simpleTree.add("Three", "Seven");
        String result = "";
        for (String s: simpleTree) {
            result += s + " ";
        }
        assertThat(result, is("One Two Three Four Five Six Seven "));
    }

}