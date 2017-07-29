package ru.job4j.tracker;

import java.util.*;

/**
* Class represents Tracker.
* @since 28/07/2017
* @version 1
*/
public class Tracker {

	/**
	* variable represents array of items.
	**/
	private Item[] items = new Item[100];

	/**
	* variable represents number of items.
	**/
	private int numberOfItems = 0;

	/**
	* variable for randomizer.
	**/
	private static final Random RN = new Random();

	/**
	* Default constructor.
	**/
	public Tracker() {
	}

	/**
	* Method adds new items to array.
	* @param item new item to add
	* @return item
	**/
	public Item add(Item item) {
		if (numberOfItems < items.length && item != null) {
			item.setID(generateID());
			items[numberOfItems] = item;
			numberOfItems++;
		} else {
			// expanding Item type array and repeating add operation
			// if array is overloaded
			Item[] newItems = new Item[items.length + 100];
			System.arraycopy(items, 0, newItems, 0, items.length);
			items = newItems;
			add(item);
		}
		return item;
	}

	/**
	* Method generates ID.
	* @return id
	**/
	String generateID() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	/**
	* Method updates item based on id.
	* @param item new item to update
	**/
	public void update(Item item) {
		for (int i = 0; i < numberOfItems; i++) {
			if (items[i].getID().equals(item.getID())) {
				items[i] = item;
				break;
			}
		}
	}

	/**
	* Method removes item based on id.
	* @param item new item to remove
	**/
	public void delete(Item item) {
		for (int i = 0; i < numberOfItems; i++) {
			if (items[i].getID().equals(item.getID())) {
				// moving array to the left
				Item[] helper = new Item[numberOfItems - i - 1];
				System.arraycopy(items, i + 1, helper, 0, numberOfItems - i - 1);
				System.arraycopy(helper, 0, items, i, numberOfItems - i - 1);
				items[numberOfItems - 1] = null;
				numberOfItems--;
				break;
			}
		}
	}

	/**
	* Method returns no null array .
	* @return result array
	**/
	public Item[] findAll() {
		Item[] result = new Item[numberOfItems];
		System.arraycopy(items, 0, result, 0, numberOfItems);
		return result;
	}

	/**
	* Method returns array by name.
	* @return result array
	**/
	public Item[] findByName(String key) {
		int count = 0;
		for (Item item : items) {
			if (item != null && item.getName().equals(key)) {
				count++;
			}
		}
		Item[] result = null;
		if (count > 0) {
			result = new Item[count];
			int i = 0;
			for (Item item : items) {
				if (item != null && item.getName().equals(key)) {
					result[i] = item;
					i++;
				}
			}
		}
		return result;
	}

	/**
	* Method returns array by id.
	* @return result
	**/
	public Item findByID(String id) {
		Item result = null;
		for (Item item : items) {
			if (item != null && item.getID().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
}