package ru.job4j.taskfortest.patternDAO.daos;

import ru.job4j.taskfortest.entities.MusicType;
import ru.job4j.taskfortest.patternDAO.EntityDao;

import java.util.List;

/**
 * Class for music type database access (DAO).
 *
 * @since 19/01/2018
 * @version 1
 */
class MusicTypeDao implements EntityDao<MusicType>{

    @Override
    public void create(MusicType entity) {

    }

    @Override
    public List<MusicType> getAll() {
        return null;
    }

    @Override
    public MusicType getEntityById(int id) {
        return null;
    }

    @Override
    public MusicType update(MusicType entity) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
