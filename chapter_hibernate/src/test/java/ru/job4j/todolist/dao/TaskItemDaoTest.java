package ru.job4j.todolist.dao;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.todolist.model.TaskItem;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test for dao.
 *
 * @since 27/04/2018
 * @version 1
 */
public class TaskItemDaoTest {

    private TaskItemDao dao = TaskItemDao.getInstance();

    @Before
    public void init() {
        dao.deleteAll();
    }

    @Test
    public void saveNewItemAndTakeAllFromDbTest() {
        TaskItem item = new TaskItem();
        item.setDesc("test");

        dao.save(item);

        List<TaskItem> list = dao.getAllItems();
        TaskItem result = list.get(0);
        assertEquals("test", result.getDesc());
    }
}