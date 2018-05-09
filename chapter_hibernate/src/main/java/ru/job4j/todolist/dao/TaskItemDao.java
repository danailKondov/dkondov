package ru.job4j.todolist.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todolist.model.TaskItem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    public void save(TaskItem taskItem) {
        Session session = null;
        try {
            // Chapter 13 of the Hibernate community docs discusses best practice,
            // with examples. The recommended paradigm is one unit of work per session.
            session = factory.openSession(); // универсальный коннект к БД
            session.beginTransaction();
            session.save(taskItem);
            session.getTransaction().commit();
            LOG.info("New item was added to DB: " + taskItem.toString());
        } finally {
            closeIfNotNull(session);
        }
    }

    private void closeIfNotNull(Session session) {
        if (session != null) {
            session.close();
        }
    }

    public List<TaskItem> getAllItems() {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction(); // нужна ли транзакция при чтении?
            List<TaskItem> list = session.createQuery("from TaskItem").list();
            session.getTransaction().commit();
            LOG.info("All items was fetch from DB");
            return list;
        } finally {
            closeIfNotNull(session);
        }
    }

    public void deleteAll() {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.createQuery("delete from TaskItem").executeUpdate();
            session.getTransaction().commit();
            LOG.info("All items was deleted");
        } finally {
            closeIfNotNull(session);
        }
    }

    public void close() {
        if (factory != null) {
            factory.close();
            LOG.info("DB (session factory) was closed");
        }
    }
}
