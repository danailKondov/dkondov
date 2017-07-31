package ru.job4j.tracker;


/**
* Class is start point with main method.
* @since 29/07/2017
* @version 1
*/
public class StartUI {

	/**
	* Input operations.
	**/
	private Input input;
	
	/**
	* Tracker.
	**/
	private Tracker tracker;

	/**
	* Exit constant.
	**/
	private static final String EXIT = "6";

	/**
	* Constructor with parameters.
	**/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	* Main method.
	**/
	public static void main(String[] args) {
		Input consoleInput = new ConsoleInput();
		Tracker tracker = new Tracker();
		new StartUI(consoleInput, tracker).init();
	}

	/**
	* General loop
	**/
	public void init() {
		while(true) {
			System.out.println(
			"0. Add new Item\n" + 
			"1. Show all items\n" + 
			"2. Edit item\n" + 
			"3. Delete item\n" + 
			"4. Find item by Id\n" + 
			"5. Find items by name\n" + 
			"6. Exit Program\n" + 
			"Select:"
			);
			String answer = input.ask("");
			if (answer.equals(EXIT)) {
				break;
			} else if (answer.equals("0")) {
				addNewItem();
			} else if (answer.equals("1")) {
				showAllItems();
			} else if (answer.equals("2")) {
				editItem();
			} else if (answer.equals("3")) {
				deleteItem();
			} else if (answer.equals("4")) {
				findItemByID();
			} else if (answer.equals("5")) {
				findItemsByName();
			} else {
				System.out.println("Wrong command, enter new one: ");
			}
		}
	}

	/**
	* Add new item option functionality.
	**/
	private void addNewItem() {
		String name = input.ask("Enter user name: ");
		String description = input.ask("Enter description: ");
		//String cr = input.ask("Enter time of creation: ");
		//long created = 0L;
		//try {
		//	created = Long.parseLong(cr); // try-catch
		//} catch (NumberFormatException e) {
		//	e.printStackTrace();
		//}
		tracker.add(new Item(name, description, 130L));
	}

	/**
	* Show all items option functionality.
	**/
	private void showAllItems() {
		for (Item item : tracker.findAll()) {
			System.out.println(item);
		}
	}

	/**
	* Edit item option functionality.
	**/
	private void editItem() {
		String id = input.ask("Enter id of edited item: ");
		String name = input.ask("Enter new name: ");
		String desc = input.ask("Enter new description: ");
		long created = tracker.findByID(id).getCreated();
		Item newItem = new Item(name, desc, created);
		newItem.setID(id);
		tracker.update(newItem);
	}

	/**
	* Delete item option functionality.
	**/
	private void deleteItem() {
		String id = input.ask("Enter id: ");
		tracker.delete(tracker.findByID(id));
	}

	/**
	* Find item by ID option functionality.
	**/
	private void findItemByID() {
		String id = input.ask("Enter id: ");
		System.out.println(tracker.findByID(id));
	}

	/**
	* Find items by name option functionality.
	**/
	private void findItemsByName() {
		String name = input.ask("Enter name: ");
		for (Item item : tracker.findByName(name)) {
			System.out.println(item);
		}
	}
}