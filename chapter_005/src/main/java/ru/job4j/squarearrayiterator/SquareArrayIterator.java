package ru.job4j.squarearrayiterator;

import java.util.Iterator;

/**
 * Class iterator for square array.
 *
 * @since 14/08/2017
 * @version 1
 */
public class SquareArrayIterator implements Iterator<Integer> {

    /**
     * Square array to iterate.
     */
    private int[][] source;

    /**
     * Row index.
     */
    private int row = 0;

    /**
     * Column index.
     */
    private int column = 0;

    /**
     * Constructor.
     * @param source array to iterate
     */
    public SquareArrayIterator(int[][] source) {
        this.source = source;
    }

    /**
     * Check is there next element in this array.
     * @return result
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if(column == source[row].length && row == source.length - 1) {
            result = false;
        }
        return result;
    }

    /**
     * Return element and move pointer further.
     * @return element
     */
    @Override
    public Integer next() {
        if (column == source[row].length) {
            row++;
            column = 0;
        }
        return source[row][column++];
    }
}
