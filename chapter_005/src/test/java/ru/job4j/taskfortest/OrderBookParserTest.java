package ru.job4j.taskfortest;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Class tests order book parser.
 *
 * @since 11/09/2017
 * @version 1
 */
public class OrderBookParserTest {

    /**
     * Test of XML book parser speed ( < 6 000 ms)
     */
    @Test
    public void orderBookParserTest() {

        long start = System.currentTimeMillis();
        OrderBookExecutor bookExecutor = new OrderBookExecutor();
        bookExecutor.execute();
        long end = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (end - start));
        assertTrue((end - start) < 6000);

    }

}