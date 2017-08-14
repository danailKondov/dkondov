package ru.job4j.squarearrayiterator;

import java.util.Iterator;

/**
 * Class square array.
 *
 * @since 14/08/2017
 * @version 1
 */
public class SquareArray implements Iterable<Integer> {

    /**
     * Square array to iterate.
     */
    private int[][] source;

    /**
     * Constructor.
     * @param source array to iterate
     */
    public SquareArray(int[][] source) {
        this.source = source;
    }

    /**
     * Iterator.
     * @return iterator
     */
    @Override
    public Iterator<Integer> iterator() {
        return new SquareArrayIterator(source);
    }
}
