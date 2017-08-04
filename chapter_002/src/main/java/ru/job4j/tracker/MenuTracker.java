package ru.job4j.tracker;


/**
* Menu tracker.
* @since 02/07/2017
* @version 1
*/
public class MenuTracker {

	/**
	* Input operations.
	**/
	private Input input;

	/**
	* Tracker.
	**/
	private Tracker tracker;

	/**
	* User actions array.
	**/
	private UserAction[] actions = new UserAction[6];

	/**
	* Constructor with parameters.
	**/
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	* Method fills user actions array.
	**/
	public void fillActions() {
		actions[0] = new AddItem(); // this.new AddItem()?
		actions[1] = new MenuTracker.ShowAllItems(); // static inner class
		actions[2] = new EditItem();
		actions[3] = new DeleteItem();
		actions[4] = new FindItemByID();
		actions[5] = new FindItemsByName(); // outer class in one faile
	}

	/**
	 * Method returns numbers of menu options
	 * @return array of menu options
	 */
	public int[] keys() {
		// +1 for "Exit" option
		int[] result = new int[actions.length + 1];
		for (int i = 0; i < actions.length + 1; i++) {
			result[i] = i;
		}
		return result;
	}

	/**
	* Method execute action by key.
	* @param key - actions id
	**/
	public void select(int key) {
		// Для внутреннего класса явная передача параметров
		// типа actions[key].execute(input, tracker) не нужна: внутренний
		// класс имеет доступ к полям внешнего?
		actions[key].execute(input, tracker); 
	}

	/**
	* Method shows all actions available.
	**/
	public void show() {
		for (UserAction action : actions) {
			System.out.println(action.info());
		}
	}

	/**
	* Add new item option functionality.
	**/
	private class AddItem implements UserAction {

		/**
		* Action identificator.
		* @returns action id
		**/
		public int key() {
			return 0;
		}

		/**
		* Method execute action.
		* @param input - class, which implements Input interface
		* @param tracker - tracker
		**/
		public void execute(Input input, Tracker tracker) {
			String name = input.ask("Enter user name: ");
			String description = input.ask("Enter description: ");
			tracker.add(new Item(name, description, 130L));
		}

		/**
		* Information about action.
		* @returns info about action
		**/
		public String info() {
			return String.format("%s. Add new Item", key());
		}
	}

	/**
	* Show all items option functionality.
	**/
	private static class ShowAllItems implements UserAction {

		/**
		* Action identificator.
		* @returns action id
		**/
		public int key() {
			return 1;
		}

		/**
		* Method execute action.
		* @param input - class, which implements Input interface
		* @param tracker - tracker
		**/
		public void execute(Input input, Tracker tracker) {
			for (Item item : tracker.findAll()) {
				System.out.println(item);
			}
		}

		/**
		* Information about action.
		* @returns info about action
		**/
		public String info() {
			return String.format("%s. Show all items", key());
		}
	}

	/**
	* Edit item option functionality.
	**/
	private class EditItem implements UserAction {

		/**
		* Action identificator.
		* @returns action id
		**/
		public int key() {
			return 2;
		}

		/**
		* Method execute action.
		* @param input - class, which implements Input interface
		* @param tracker - tracker
		**/
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Enter id of edited item: ");
			String name = input.ask("Enter new name: ");
			String desc = input.ask("Enter new description: ");
			long created = tracker.findByID(id).getCreated();
			Item newItem = new Item(name, desc, created);
			newItem.setID(id);
			tracker.update(newItem);
		}

		/**
		* Information about action.
		* @returns info about action
		**/
		public String info() {
			return String.format("%s. Edit item", key());
		}
	}

	/**
	* Delete item option functionality.
	**/
	private class DeleteItem implements UserAction {

		/**
		* Action identificator.
		* @returns action id
		**/
		public int key() {
			return 3;
		}

		/**
		* Method execute action.
		* @param input - class, which implements Input interface
		* @param tracker - tracker
		**/
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Enter id: ");
			tracker.delete(tracker.findByID(id));
		}

		/**
		* Information about action.
		* @returns info about action
		**/
		public String info() {
			return String.format("%s. Delete item", key());
		}
	}

	/**
	* Find item by ID option functionality.
	**/
	private class FindItemByID implements UserAction {

		/**
		* Action identificator.
		* @returns action id
		**/
		public int key() {
			return 4;
		}

		/**
		* Method execute action.
		* @param input - class, which implements Input interface
		* @param tracker - tracker
		**/
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Enter id: ");
			System.out.println(tracker.findByID(id));
		}

		/**
		* Information about action.
		* @returns info about action
		**/
		public String info() {
			return String.format("%s. Find item by Id", key());
		}
	}

	
}

/**
* Find items by name option functionality.
**/
class FindItemsByName implements UserAction {

	/**
	* Action identificator.
	* @returns action id
	**/
	public int key() {
		return 5;
	}

	/**
	* Method execute action.
	* @param input - class, which implements Input interface
	* @param tracker - tracker
	**/
	public void execute(Input input, Tracker tracker) {
		String name = input.ask("Enter name: ");
		for (Item item : tracker.findByName(name)) {
			System.out.println(item);
		}
	}

	/**
	* Information about action.
	* @returns info about action
	**/
	public String info() {
		return String.format("%s. Find items by name", key());
	}
}