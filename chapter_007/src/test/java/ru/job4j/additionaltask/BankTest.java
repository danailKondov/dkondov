package ru.job4j.additionaltask;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class tests rush hors finding methods.
 *
 * @since 23/10/2017
 * @version 1
 */
public class BankTest {

    /**
     * Tests findRushHours method.
     */
    @Test
    public void findRushHoursTest() {
        Bank bank = new Bank();

        bank.addCustomer(new Customer("Peter", new TimeDistance(1, 4)));
        bank.addCustomer(new Customer("Pen", new TimeDistance(2, 3)));
        bank.addCustomer(new Customer("Masha", new TimeDistance(2, 5)));
        bank.addCustomer(new Customer("Medved", new TimeDistance(6, 7)));
        bank.addCustomer(new Customer("Preved", new TimeDistance(4, 7)));
        bank.addCustomer(new Customer("Hello", new TimeDistance(3, 5)));

        List<TimeDistance> rushHours = bank.findRushHour();
        String expected = "[2-3, 4-5, 3-4]";
        assertThat(rushHours.toString(), is (expected));
    }
}