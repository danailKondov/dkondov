package ru.job4j.additionaltask;

/**
 * Class is board of the game.
 *
 * @since 22/10/2017
 * @version 1
 */
public class Customer {

    /**
     * Customer's name.
     */
    private final String name;

    /**
     * Time customer passed in bank.
     */
    private final TimeDistance timeInBank;

    public Customer(String name, TimeDistance timeInBank) {
        this.name = name;
        this.timeInBank = timeInBank;
    }

    public String getName() {
        return name;
    }

    public TimeDistance getTimeInBank() {
        return timeInBank;
    }
}
