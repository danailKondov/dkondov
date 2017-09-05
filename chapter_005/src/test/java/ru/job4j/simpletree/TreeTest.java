package ru.job4j.simpletree;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class tests simple tree.
 *
 * @since 03/09/2017
 * @version 1
 */
public class TreeTest {

    /**
     * Test for add method and iterator.
     */
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

    /**
     * Test if tree is binary when it's.
     */
    @Test
    public void whenTreeIsBinaryTest() {
        Tree<String> simpleTree = new Tree<>();
        simpleTree.add("One", "Two");
        simpleTree.add("One", "Three");
        simpleTree.add("Two", "Four");
        simpleTree.add("Two", "Five");
        simpleTree.add("Three", "Six");
        simpleTree.add("Three", "Seven");
        assertTrue(simpleTree.isBinary());
    }

    /**
     * Test if tree is binary when it's not.
     */
    @Test
    public void whenTreeIsNotBinaryTest() {
        Tree<String> simpleTree = new Tree<>();
        simpleTree.add("One", "Two");
        simpleTree.add("One", "Three");
        simpleTree.add("Two", "Four");
        simpleTree.add("Two", "Five");
        simpleTree.add("Three", "Six");
        simpleTree.add("Three", "Seven");
        simpleTree.add("Three", "Eight");
        assertFalse(simpleTree.isBinary());
    }

}