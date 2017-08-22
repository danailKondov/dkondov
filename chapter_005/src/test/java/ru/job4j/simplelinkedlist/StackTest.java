package ru.job4j.simplelinkedlist;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests simple stack.
 *
 * @since 22/08/2017
 * @version 1
 */
public class StackTest {

    /**
     * Test for stack methods.
     */
    @Test
    public void pushPopPeekTest() {
        Stack<String> stack = new Stack<>();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        stack.push("Four");
        stack.push("Five");
        String result = stack.peek();
        assertThat(result, is("Five"));
        result = "";
        while (!stack.empty()) {
            result += stack.pop();
        }
        assertThat(result, is("FiveFourThreeTwoOne"));
    }
}