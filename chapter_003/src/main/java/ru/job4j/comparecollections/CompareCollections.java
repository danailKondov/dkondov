package ru.job4j.comparecollections;

import java.util.*;

/**
 * Class compares different collections.
 *
 * @since 09/08/2017
 * @version 1
 */
public class CompareCollections {

    /**
     * Main method.
     * @param args - arguments of the program
     */
    public static void main(String[] args) {
        new CompareCollections().testAddAndDeleteMethods();
    }

    /**
     * Method for testing time, needed fo different types of
     * collections to do add and delete operations
     */
    public void testAddAndDeleteMethods() {
        System.out.println("Testing add method with 0.1 mln elements: ");
        System.out.println("LinkedList: " + add(new LinkedList<>(), 100000) + " ms");
        System.out.println("ArrayList: " + add(new ArrayList<>(), 100000) + " ms");
        System.out.println("TreeSet: " + add(new TreeSet<>(), 100000) + " ms");
        System.out.println("------------------------------");
        System.out.println("Testing delete method with 0.1 mln elements: ");
        System.out.println("LinkedList: " + delete(new LinkedList<>(), 100000) + " ms");
        System.out.println("ArrayList: " + delete(new ArrayList<>(), 100000) + " ms");
        System.out.println("TreeSet: " + delete(new TreeSet<>(), 100000) + " ms");

    }

    /**
     * Add operation.
     * @param collection - collection for testing
     * @param amount - number of add operations
     * @return time in ms
     */
    public long add(Collection<String> collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf((int)(Math.random()*100)));
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     * Delete operation.
     * @param collection - collection for testing
     * @param amount - number of add operations
     * @return time in ms
     */
    public long delete(Collection<String> collection, int amount) {
        for (int i = 0; i < amount; i++) {
            collection.add(String.valueOf((int)(Math.random()*1000)));
        }
        long startTime = System.currentTimeMillis();
        Iterator<String> iterator = collection.iterator();
        while(iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
