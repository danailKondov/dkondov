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
			if (answer.equals("6")) {
				break;
			} else if (answer.equals("0")) {
				String name = input.ask("Enter user name: ");
				String description = input.ask("Enter description: ");
				tracker.add(new Item(name, description, 130L));
			} else if (answer.equals("1")) {
				for (Item item : tracker.findAll()) {
					System.out.println(item);
				}
			} else if (answer.equals("2")) {
				String id = input.ask("Enter id of edited item: ");
				String name = input.ask("Enter new name: ");
				String desc = input.ask("Enter new description: ");
				long created = tracker.findByID(id).getCreated();
				Item newItem = new Item(name, desc, created);
				newItem.setID(id);
				tracker.update(newItem);
			} else if (answer.equals("3")) {
				String id = input.ask("Enter id: ");
				tracker.delete(tracker.findByID(id));
			} else if (answer.equals("4")) {
				String id = input.ask("Enter id: ");
				System.out.println(tracker.findByID(id));
			} else if (answer.equals("5")) {
				String name = input.ask("Enter name: ");
				for (Item item : tracker.findByName(name)) {
					System.out.println(item);
				}
			} else {
				System.out.println("Wrong command, enter new one: ");
			}
		}
	}
}