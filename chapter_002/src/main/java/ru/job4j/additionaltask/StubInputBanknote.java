package ru.job4j.additionaltask;

/**
 * Simulation of banknote input for testing.
 * @since 06/08/2017
 * @version 1
 */
public class StubInputBanknote implements Input {

    /**
     * Array of banknotes to enter.
     */
    private int[] banknote;

    /**
     * Counter of banknotes.
     */
    private int position = 0;

    /**
     * Constructor with parameters.
     * @param banknote - array of banknotes.
     */
    public StubInputBanknote(int[] banknote) {
        this.banknote = banknote;
    }

    /**
     * Method simulate process of entering banknotes to ATM.
     * @return - banknote nominal
     */
    @Override
    public int enterBanknote() {
        return banknote[position++];
    }
}
