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

    /**
     * Test add to binary tree.
     */
    @Test
    public void whenAddToBinaryTreeTest() {
        Tree<Integer> integerTree = new Tree<>();
        integerTree.add(15);
        integerTree.add(20);
        integerTree.add(75);
        integerTree.add(1);
        integerTree.add(19);
        integerTree.add(98);
        integerTree.add(155);
        integerTree.add(6);
        integerTree.add(4);
        integerTree.add(4);
        integerTree.add(4);
        assertTrue(integerTree.isBinary());
        assertFalse(integerTree.add(4));
    }

}