package ru.job4j.additionaltask;

import java.util.ArrayList;
import java.util.List;

/**
 * Exchanger.
 *
 * Values of coins is fixed: 1, 5 and 10.
 *
 * @since 06/08/2017
 * @version 1
 */
public class Exchanger {

    /**
     * Input class for entering banknote.
     */
    private Input input;

    /**
     * List of coins combinations - result of exchange.
     */
    private List<List<Integer>> result = new ArrayList<>();

    /**
     * Constructor.
     * @param input - class for entering banknotes
     */
    public Exchanger(Input input) {
        this.input = input;
    }

    /**
     * Main method.
     * @param args - parameters
     */
    public static void main(String[] args) {
        Input input = new BaseInput();
        List<List<Integer>> list = new Exchanger(input).exchange();
        System.out.println(list);
    }

    /**
     * Method exchanges banknote for coins
     * @return result list of coins combinations
     */
    public List<List<Integer>> exchange() {
        int source = input.enterBanknote();
        int sum = 0;
        for (int i = 0; i <= source/10; i++) {
            for (int j = 0; j <= source/5; j++) {
                for (int k = 0; k <= source; k++) {
                    sum = 10*i + 5*j + k;
                    if (sum == source) addToList(i, j, k);
                }
            }

        }
        return result;
    }

    /**
     * Method forms list of coins and adds him to result list.
     * @param i - number of coins with value 10
     * @param j - number of coins with value 5
     * @param k - number of coins with value 1
     */
    private void addToList(int i, int j, int k) {
        List<Integer> list = new ArrayList<>();
        for (int l = 0; l < i; l++) {
            list.add(10);
        }
        for (int l = 0; l < j; l++) {
            list.add(5);
        }
        for (int l = 0; l < k; l++) {
            list.add(1);
        }
        result.add(list);
    }

    /**
     * Getter for result.
     * @return result list of coins combinations
     */
    public List<List<Integer>> getResult() {
        return result;
    }
}
