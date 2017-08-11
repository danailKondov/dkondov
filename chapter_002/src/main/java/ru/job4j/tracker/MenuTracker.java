package ru.job4j.tracker;


import java.util.ArrayList;

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
	private ArrayList<UserAction> actions = new ArrayList<>();

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
		actions.add(new AddItem("Add new Item", 0)); // this.new AddItem()?
		actions.add(new MenuTracker.ShowAllItems("Show all items", 1)); // static inner class
		actions.add(new EditItem("Edit item", 2));
		actions.add(new DeleteItem("Delete item", 3));
		actions.add(new FindItemByID("Find item by Id", 4));
		actions.add(new FindItemsByName("Find items by name", 5)); // outer class in one file
	}

	/**
	 * Method returns numbers of menu options
	 * @return list of menu options
	 */
	public ArrayList<Integer> keys() {
		// +1 for "Exit" option
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < actions.size() + 1; i++) {
			result.add(i);
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
		actions.get(key).execute(input, tracker);
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
	private class AddItem extends BaseAction {

        /**
         * Constructor with parameters.
         **/
        private AddItem(String name, int key) {
            super(name, key);
        }

        /**
		* Action identification.
		* @return action id
		**/
		public int key() {
			return super.key;
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
	}

	/**
	* Show all items option functionality.
	**/
	private static class ShowAllItems extends BaseAction {

        /**
         * Constructor with parameters.
         **/
        private ShowAllItems(String name, int key) {
            super(name, key);
        }

        /**
		* Action identificator.
		* @return action id
		**/
		public int key() {
			return super.key;
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
	}

	/**
	* Edit item option functionality.
	**/
	private class EditItem extends BaseAction {

        /**
         * Constructor with parameters.
         **/
        private EditItem(String name, int key) {
            super(name, key);
        }

        /**
		* Action identificator.
		* @return action id
		**/
		public int key() {
			return super.key;
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
	}

	/**
	* Delete item option functionality.
	**/
	private class DeleteItem extends BaseAction {

        /**
         * Constructor with parameters.
         **/
        private DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
		* Action identificator.
		* @return action id
		**/
		public int key() {
			return super.key;
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
	}

	/**
	* Find item by ID option functionality.
	**/
	private class FindItemByID extends BaseAction {

        /**
         * Constructor with parameters.
         **/
        private FindItemByID(String name, int key) {
            super(name, key);
        }

        /**
		* Action identificator.
		* @return action id
		**/
		public int key() {
			return super.key;
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
	}
}

/**
* Find items by name option functionality.
**/
class FindItemsByName extends BaseAction {

    /**
     * Constructor with parameters.
     **/
    public FindItemsByName(String name, int key) {
        super(name, key);
    }

    /**
	* Action identificator.
	* @return action id
	**/
	public int key() {
		return super.key;
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
}