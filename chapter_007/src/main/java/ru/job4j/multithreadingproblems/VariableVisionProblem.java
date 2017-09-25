package ru.job4j.multithreadingproblems;

/**
 * Class for variable vision and reordering problem demonstration .
 *
 * @since 26/09/2017
 * @version 1
 */
public class VariableVisionProblem {

    public int data = 0;
    public boolean run = true;

    /**
     * Variable vision and reordering demonstration. Need
     * "volatile" keyword for "data" and "run" fields to run
     * properly.
     *
     * @return modified (with some luck) data
     */
    public int go() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                data = 1;
                run = false;
            }
        }).start();

        while(run) {/*NOP*/}
        return data;
    }
}
