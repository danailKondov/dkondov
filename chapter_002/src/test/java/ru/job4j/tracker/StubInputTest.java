package ru.job4j.tracker;

import org.junit.Test;
import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing StartUI class.
* @since 30/07/2017
* @version 1
**/
public class StubInputTest {

	/**
	* Method for testing add menu option ("0").
	**/
	@Test
	public void testOfAddMenuOption() {
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findAll()[0].getName(), is("test name"));
	}

	/**
	* Method for testing show all items option ("1").
	**/
	@Test
	public void testOfShowAllItemsMenuOption() {
		Tracker tracker = new Tracker();
		Item one = new Item("nameOne", "descOne", 121L);
		tracker.add(one);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Input input = new StubInput(new String[]{"1", "6"});
		new StartUI(input, tracker).init();
		String sep = System.getProperty("line.separator");
		String menu = "0. Add new Item\r\n" + 
			"1. Show all items\r\n" + 
			"2. Edit item\r\n" + 
			"3. Delete item\r\n" + 
			"4. Find item by Id\r\n" + 
			"5. Find items by name\r\n" + 
			"6. Exit Program\n" + 
			"Select:" + 
			sep;
		String result =	menu + one.toString() + sep + menu;
		String	expected = out.toString();
		assertThat(expected, is(result));
	}

	/**
	* Method for testing edit item option ("2").
	**/
	@Test
	public void testOfEditItemMenuOption() {
		Tracker tracker = new Tracker();
		Item one = new Item("nameOne", "descOne", 121L);
		tracker.add(one);
		Input input = new StubInput(new String[]{"2", one.getID(), "new name", "new desc", "6"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findByID(one.getID()).getName(), is("new name"));
	}

	/**
	* Method for testing delete item option ("3").
	**/
	@Test
	public void testOfDeleteItemMenuOption() {
		Tracker tracker = new Tracker();
		Item one = new Item("nameOne", "descOne", 121L);
		tracker.add(one);
		Input input = new StubInput(new String[]{"3", one.getID(), "6"});
		new StartUI(input, tracker).init();
		Item expected = null;
		assertThat(tracker.findByID(one.getID()), is(expected));
	}

	/**
	* Method for testing find item option ("4").
	**/
	@Test
	public void testOfFindItemMenuOption() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item one = new Item("nameOne", "descOne", 121L);
		tracker.add(one);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Input input = new StubInput(new String[]{"4", one.getID(), "6"});
		new StartUI(input, tracker).init();
		String sep = System.getProperty("line.separator");
		String menu = "0. Add new Item\r\n" + 
			"1. Show all items\r\n" + 
			"2. Edit item\r\n" + 
			"3. Delete item\r\n" + 
			"4. Find item by Id\r\n" + 
			"5. Find items by name\r\n" + 
			"6. Exit Program\n" + 
			"Select:" + 
			sep;
		String result =	menu + one.toString() + sep + menu;
		String	expected = out.toString();
		assertThat(expected, is(result));
	}
	
	/**
	* Method for testing find by name option ("5").
	**/
	@Test
	public void testOfFindByNameMenuOption() {
		Tracker tracker = new Tracker();
		tracker.add(new Item("test1", "testDescription", 123L));
		tracker.add(new Item("test2", "testDescription2", 124L));
		tracker.add(new Item("test3", "testDescription3", 125L));
		Item one = new Item("nameOne", "descOne", 121L);
		tracker.add(one);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Input input = new StubInput(new String[]{"5", "nameOne", "6"});
		new StartUI(input, tracker).init();
		String sep = System.getProperty("line.separator");
		String menu = "0. Add new Item\r\n" + 
			"1. Show all items\r\n" + 
			"2. Edit item\r\n" + 
			"3. Delete item\r\n" + 
			"4. Find item by Id\r\n" + 
			"5. Find items by name\r\n" + 
			"6. Exit Program\n" + 
			"Select:" + 
			sep;
		String result =	menu + one.toString() + sep + menu;
		String	expected = out.toString();
		assertThat(expected, is(result));
	}
}