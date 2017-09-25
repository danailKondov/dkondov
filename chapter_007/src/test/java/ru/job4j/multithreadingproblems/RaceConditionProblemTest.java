package ru.job4j.multithreadingproblems;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests different multithreading problems.
 *
 * @since 26/09/2017
 * @version 1
 */
public class RaceConditionProblemTest {

    /**
     * Tests race condition (data races).
     */
    @Test
    public void whenRaceConditionIsOnTest() {
        RaceConditionProblem problem = new RaceConditionProblem();
        int expected = 1_000_000;
        int result = problem.incrementation(expected);
        assertTrue(result < expected);
    }

    /**
     * Test visibility and reordering problems.
     */
    @Test
    public void whenVisibilityOrReorderingProblemTest() {
        VariableVisionProblem problem = new VariableVisionProblem();
        int result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result = problem.go();
            if (result == 0) break;
        }
        assertThat(result, is (1));
    }

}