package ru.job4j.taskfortest.patternDAO;

import java.util.List;

/**
 * Class is interface for all DAO objects.
 *
 * @since 19/02/2018
 * @version 1
 */
public interface EntityDao<E> {
    void create(E entity);
    List<E> getAll();
    E getEntityById(int id);
    E update(E entity);
    void deleteById(int id);
}
