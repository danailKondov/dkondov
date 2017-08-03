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
	private static final int EXIT = 6;

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
		Input consoleInput = new ValidateInput();
		Tracker tracker = new Tracker();
		new StartUI(consoleInput, tracker).init();
	}

	/**
	* General loop
	**/
	public void init() {
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();
		while(true) {
			menu.show();
			System.out.println(
			"6. Exit Program\n" + 
			"Select:"
			);
			int key = Integer.valueOf(input.ask("", new int[] {0, 1, 2, 3, 4, 5, 6}));
			if (key == EXIT) {
				break;
			}
			menu.select(key);
		}
	}
}