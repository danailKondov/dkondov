package ru.job4j.additionaltask;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing Exchanger class.
 * @since 07/08/2017
 * @version 1
 **/
public class ExchangerTest {

    /**
     * Test for exchange.
     */
    @Test
    public void exchangeTest() {
        StubInputBanknote inputBanknote = new StubInputBanknote(new int[]{10});
        Exchanger exchanger = new Exchanger(inputBanknote);
        List<List<Integer>> resultList = exchanger.exchange();
        String result = resultList.toString();
        String expected = "[[1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [5, 1, 1, 1, 1, 1], [5, 5], [10]]";
        assertThat(result, is(expected));
    }

}