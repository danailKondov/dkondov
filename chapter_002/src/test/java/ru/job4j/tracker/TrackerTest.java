package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
* Class for testing Trecker class.
* @since 13/11/2017
* @version 2
**/
public class TrackerTest {

	final Logger log = LoggerFactory.getLogger("TrackerTest");
	Connection connection = null;

	@Before
	public void initialize() {
		Properties properties = new Properties();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/items_db", "postgres", "pass");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	* Method for testing add and findAll operations.
	**/
	@Test
	public void testAddAndFindAllOperations() {
		Tracker tracker = new Tracker(connection, log);
		Item item = new Item("test1", "testDescription", 123L);
		ArrayList<String> list = new ArrayList<>();
		list.add("comment one");
		list.add("comment two");
		list.add("comment three");
		item.setComments(list);
		tracker.add(item);
		assertTrue(tracker.findAll().contains(item));
	}

	/**
	* Method for testing update and findByID operations.
	**/
	@Test
	public void testUpdateAndFindByIDOperations() {
		Tracker tracker = new Tracker(connection, log);
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("test4", "testDescription4", 126L);
		tracker.add(item);
		String id = item.getID();
		Item itemUpdated = new Item("testUpdated", "testDescription5", 127L);
		itemUpdated.setID(id);
		tracker.update(itemUpdated);		
		assertThat(tracker.findByID(id), is(itemUpdated));
	}

	/**
	* Method for testing findByName operation.
	**/
	@Test
	public void testFindByNameOperation() {
		Tracker tracker = new Tracker(connection, log);
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("test4", "testDescription4", 126L);
		Item item2 = new Item("test4", "testDescription5", 127L);
		tracker.add(item);
		tracker.add(item2);
		assertThat(tracker.findByName("test4").get(0), is(item));
		assertThat(tracker.findByName("test4").get(1), is(item2));
	}

	/**
	* Method for testing delete operation.
	**/
	@Test
	public void testDeleteOperation() {
		Tracker tracker = new Tracker(connection, log);
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("testDelete", "testDescription4", 126L);
		tracker.add(item);
		tracker.delete(item);
		ArrayList<Item> result = tracker.findAll();
		assertFalse(result.contains(item));
	}
}