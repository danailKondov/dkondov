package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Trecker class.
* @since 29/07/2017
* @version 1
**/
public class TrackerTest {

	/**
	* Method for testing add and findAll operations.
	**/
	@Test
	public void testAddAndFindAllOperations() {
		Tracker tracker = new Tracker();
		Item item = new Item("test1", "testDescription", 123L);
		tracker.add(item);
		assertThat(tracker.findAll()[0], is(item));
	}

	/**
	* Method for testing update and findByID operations.
	**/
	@Test
	public void testUpdateAndFindByIDOperations() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("test4", "testDescription4", 126L);
		tracker.add(item);
		String id = item.getID();
		Item itemUpdated = new Item("test5", "testDescription5", 127L);
		itemUpdated.setID(id);
		tracker.update(itemUpdated);		
		assertThat(tracker.findByID(id), is(itemUpdated));
	}

	/**
	* Method for testing findByName operation.
	**/
	@Test
	public void testFindByNameOperation() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("test4", "testDescription4", 126L);
		Item item2 = new Item("test4", "testDescription5", 127L);
		tracker.add(item);
		tracker.add(item2);
		assertThat(tracker.findByName("test4")[0], is(item));
		assertThat(tracker.findByName("test4")[1], is(item2));
	}

	/**
	* Method for testing delete operation.
	**/
	@Test
	public void testDeleteOperation() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item item = new Item("test4", "testDescription4", 126L);
		tracker.add(item);
		int numberOfItemsBefore = tracker.findAll().length;
		tracker.delete(item);
		int numberOfItemsAfter = tracker.findAll().length;
		Item[] result = tracker.findByName("test4");
		Item[] expected = null;
		assertThat(result, is(expected));
		assertThat(numberOfItemsAfter, is(numberOfItemsBefore - 1));
	}
}