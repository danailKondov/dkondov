package ru.job4j.iteratorofiterators;

import java.util.Iterator;

/**
 * Interface with method to convert iterator of iterators.
 *
 * @since 16/08/2017
 * @version 1
 */
public interface ConvertIterator {

    /**
     * Coverts iterator of iterators to simple iterator.
     * @param it - iterator of iterators
     * @return - simple iterator
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
