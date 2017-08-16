package ru.job4j.iteratorofiterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class implementing method to convert iterator of iterators.
 *
 * @since 16/08/2017
 * @version 1
 */
public class SuperIteratorConverter implements ConvertIterator {

    /**
     * Coverts iterator of iterators to simple iterator.
     * @param it - iterator of iterators
     * @return - simple iterator
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        Iterator<Integer> result = new Iterator<Integer>() {

            Iterator<Integer> currentIterator = it.next();

            /**
             * Check is there next element.
             * @return result
             */
            @Override
            public boolean hasNext() {
                return currentIterator.hasNext() || it.hasNext();
            }

            /**
             * Return element and move pointer further.
             * @return element
             */
            @Override
            public Integer next() {
                if (!currentIterator.hasNext() && !it.hasNext()) {
                    throw new NoSuchElementException();
                }

                if(!currentIterator.hasNext()) currentIterator = it.next();

                return currentIterator.next();
            }
        };
        return result;
    }
}
