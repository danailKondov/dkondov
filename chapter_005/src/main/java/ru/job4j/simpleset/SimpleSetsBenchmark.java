package ru.job4j.simpleset;

/**
 * Class to benchmark different types of sets.
 *
 * @since 26/08/2017
 * @version 1
 */
public class SimpleSetsBenchmark {

    /**
     * Adds values to sets
     * @param set to fill
     */
    private void setStart(SimpleSets<String> set) {
        for (int i = 0; i < 20000; i++) {
            set.add(String.valueOf(Math.random()*1000));
        }
        set.add("Ea"); // все 2 строки имеют один хэш-код и тем самым
        set.add("FB"); // можно протестировать работу контейнера
    }

    /**
     * Tests time needed for add operation
     * @param set to test
     * @return time in milliseconds
     */
    public long benchmarkSetsOnAdd(SimpleSets<String> set) {
        long start = System.currentTimeMillis();
        setStart(set);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * Tests time needed for contains operation
     * @param set to test
     * @return time in nanoseconds
     */
    public long benchmarkSetsOnContains(SimpleSets<String> set) {
        setStart(set);
        long start = System.nanoTime();
        set.contains("FB");
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        SimpleSetsBenchmark benchmark = new SimpleSetsBenchmark();
        System.out.println("===============================");
        System.out.println("Testing add method: ");
        SimpleSet<String> simpleSet = new SimpleSet<String>();
        System.out.println("Set on simple array base: " + benchmark.benchmarkSetsOnAdd(simpleSet) + " ms");
        SimpleSetOnLinkedList<String> setOnLinkedList = new SimpleSetOnLinkedList<>();
        System.out.println("Set on linked list base: " + benchmark.benchmarkSetsOnAdd(setOnLinkedList) + " ms");
        SimpleHashSet<String> simpleHashSet = new SimpleHashSet<>();
        System.out.println("Set on hash table base: " + benchmark.benchmarkSetsOnAdd(simpleHashSet) + " ms");
        System.out.println();
        System.out.println("================================");
        System.out.println("Testing contains method: ");
        System.out.println("Set on simple array base: " + (float)benchmark.benchmarkSetsOnContains(simpleSet)/1000000 + " ms");
        System.out.println("Set on linked list base: " + (float)benchmark.benchmarkSetsOnContains(setOnLinkedList)/1000000 + " ms");
        System.out.println("Set on hash table base: " + (float)benchmark.benchmarkSetsOnContains(simpleHashSet)/1000000 + " ms");
    }
}
