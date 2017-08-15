package ru.job4j.evennumberiterator;

import java.util.Iterator;

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

            @Override
            public boolean hasNext() {
                boolean result = false;
                if(pointer != source.length && source[pointer]%2 == 0) {
                    return true;
                }
                return result;
            }

            @Override
            public Integer next() {
                return source[pointer++];
            }
        };
    }
}
