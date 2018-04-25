package ru.job4j.taskfortest.patternDAO.daos;

import ru.job4j.taskfortest.entities.User;
import ru.job4j.taskfortest.patternDAO.EntityDao;

import java.util.List;

/**
 * Class for user database access (DAO).
 *
 * @since 19/01/2018
 * @version 1
 */
class UserDao implements EntityDao<User>{

    @Override
    public void create(User entity) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getEntityById(int id) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
