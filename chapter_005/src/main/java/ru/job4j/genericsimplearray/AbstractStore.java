package ru.job4j.genericsimplearray;

/**
 * Class parent for all stores.
 *
 * @since 20/08/2017
 * @version 1
 */
public abstract class AbstractStore<T> implements Store {

    /**
     * Database for items.
     */
    protected SimpleArray<T> store;

    public AbstractStore(SimpleArray<T> store) {
        this.store = store;
    }

    /**
     * Add new item.
     * @param t new item
     */
    public void add(T t) {
        store.add(t);
    }

    /**
     * Remove t.
     * @param t - t to remove
     */
    public void remove(T t) {
        Integer position = store.getPosition(t);
        if (position != null) {
            store.delete(position);
        }
    }

    /**
     * Update t by ID
     * @param t t to update
     */
    public void update(T t) {
        Integer position = store.getPosition(t);
        if (position != null) {
            store.update(position, t);
        }
    }

}
