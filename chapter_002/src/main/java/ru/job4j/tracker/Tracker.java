package ru.job4j.tracker;

import java.util.*;

/**
* Class represents Tracker.
* @since 28/07/2017
* @version 1
*/
public class Tracker {

	/**
	* variable represents list of items.
	**/
	private ArrayList<Item> items = new ArrayList<>();

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
		if (item != null) {
			item.setID(generateID());
			items.add(item);
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
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getID().equals(item.getID())) {
				items.set(i, item);
				break;
			}
		}
	}

	/**
	* Method removes item based on id.
	* @param item new item to remove
	**/
	public void delete(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getID().equals(item.getID())) {
				items.remove(i);
				break;
			}
		}
	}

	/**
	* Method returns no null array .
	* @return result array
	**/
	public ArrayList<Item> findAll() {
		return items;
	}

	/**
	* Method returns array by name.
	* @return result array
	**/
	public ArrayList<Item> findByName(String key) {
		ArrayList<Item> result = new ArrayList<>();
		for (Item item : items) {
			if (item.getName().equals(key)) {
				result.add(item);
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
			if (item.getID().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}
}