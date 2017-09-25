package ru.job4j.multithreadingproblems;

/**
 * Class for race condition .
 *
 * @since 26/09/2017
 * @version 1
 */
public class RaceConditionProblem {

    /**
     * Result of increment ops.
     */
    public int sum = 0;

    /**
     * Increments field sum to target value (but have to
     * fail due to data race - synchronization is needed)
     *
     * @param target value of increment ops
     * @return real value of increment ops
     */
    public int incrementation(int target) {

        Runnable increment = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < target/2; i++) {
                    sum++;
                }
            }
        };

        Thread threadOne = new Thread(increment);
        Thread threadTwo = new Thread(increment);

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sum;
    }
}
