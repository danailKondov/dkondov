package ru.job4j.evennumberiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class holding array with numbers to iterate.
 *
 * @since 14/08/2017
 * @version 1
 */
public class EvenNumbersArray implements Iterable<Integer>{

    /**
     * Source array with numbers to iterate.
     */
    private int[] source;

    /**
     * Constructor.
     * @param source - array to iterate
     */
    public EvenNumbersArray(int[] source) {
        this.source = source;
    }

    /**
     * Iterator.
     * @return iterator
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int pointer = 0;

            /**
             * Check is there next even element in this array.
             * @return result
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if(pointer != source.length && source[pointer]%2 == 0) {
                    return true;
                }
                return result;
            }

            /**
             * Return element and move pointer further.
             * @return element
             */
            @Override
            public Integer next() {
                int i;
                if ((i = source[pointer++]) % 2 == 0) {
                    return i;
                }
                throw new NoSuchElementException();
            }
        };
    }
}
