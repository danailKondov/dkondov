package ru.job4j.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Class for simple DIY non-blocking cache.
 *
 * @since 11/10/2017
 * @version 1
 */
public class NonBlockingCache {

    /**
     * Task storage.
     */
    private ConcurrentHashMap<Integer, Task> storage = new ConcurrentHashMap<>();

    /**
     * Adds new task to storage. If task with same ID
     * is already present in storage nothing will be added.
     *
     * @param task to add.
     */
    public void add(Task task) {
        storage.putIfAbsent(task.getiD(), task);
    }

    /**
     * Deletes task with same ID.
     * @param id of task to remove
     */
    public void delete(int id) {
        storage.remove(id);
    }

    /**
     * Updates task in storage.
     * @param task to update
     * @throws OptimisticException if task was already modified
     */
    public void update(Task task) throws OptimisticException {

        Task result = storage.computeIfPresent(task.getiD(), new BiFunction<Integer, Task, Task>() {
            @Override
            public Task apply(Integer integer, Task oldTask) {
                Task result = null;
                if(oldTask.getVersion() + 1 == task.getVersion()) {
                    result = task;
                }
                return result;
            }
        });

        if (result == null) throw new OptimisticException();
    }
}
