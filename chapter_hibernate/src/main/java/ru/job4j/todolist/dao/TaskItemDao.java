package ru.job4j.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.model.TaskItem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Dao for TaskItem entity.
 *
 * @since 26/04/2018
 * @version 1
 */
public class TaskItemDao {

    private static final Logger LOG = LoggerFactory.getLogger(TaskItemDao.class);
    private SessionFactory factory;

    private TaskItemDao() {
        factory = new Configuration()
                .configure() // берем установки из hibernate.cfg.xml
                .buildSessionFactory();
        LOG.info("Dao was created");
    }

    private static class TaskItemDaoHelper {
        private static final TaskItemDao INSTANCE = new TaskItemDao();
    }

    public static TaskItemDao getInstance() {
        return TaskItemDaoHelper.INSTANCE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T t = command.apply(session);
            tx.commit();
            return t;
        } catch (final Exception e) {
            tx.rollback();
            throw e;
        } finally {
            closeIfNotNull(session);
        }
    }

    public void save(TaskItem taskItem) {
        tx (session -> session.save(taskItem));
    }

    public List<TaskItem> getAllItems() {
        return tx(session -> session.createQuery("from TaskItem").list());
    }

    public void deleteAll() {
        tx (session -> session.createQuery("delete from TaskItem").executeUpdate());
    }

    private void closeIfNotNull(Session session) {
        if (session != null) {
            session.close();
        }
    }

    public void close() {
        if (factory != null) {
            factory.close();
            LOG.info("DB (session factory) was closed");
        }
    }
}
