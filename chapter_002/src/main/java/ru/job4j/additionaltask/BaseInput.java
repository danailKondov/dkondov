package ru.job4j.additionaltask;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Banknote input.
 * @since 06/08/2017
 * @version 1
 */
public class BaseInput implements Input {

    /**
     * Process of entering banknotes to ATM.
     * @return - banknote nominal
     */
    @Override
    public int enterBanknote() {
        System.out.println("Enter banknote: ");
        int nominal = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            nominal = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entered banknote is invalid");
        }
        return nominal;
    }
}
