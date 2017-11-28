package ru.job4j.xmloptimisation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class tests XML optimization.
 *
 * @since 28/11/2017
 * @version 1
 */
public class OptimizerTest {

    /**
     * Tests time performance and mean value counting.
     */
    @Test
    public void optimizerTimeAndMeanCountTest() {
        String url = "jdbc:sqlite:I:\\Ресурсы Java\\SQLite\\test_db";
        Optimizer optimizer = new Optimizer();
        optimizer.setUrl(url);
        optimizer.setNumN(1_000_000);
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                optimizer.go();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long timeElapsed = (end - start)/1000/60;
        assertTrue(timeElapsed < 5);
        assertTrue(optimizer.getMeanValue() == 500000.5);
    }

}