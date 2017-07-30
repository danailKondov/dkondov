package ru.job4j.tracker;

/**
* Class represents Item.
* @since 28/07/2017
* @version 1
*/
public class Item {

	/**
	* variable represents item identificator.
	**/
	private String id;

	/**
	* variable represents name.
	**/
	private String name;

	/**
	* variable represents item description.
	**/
	private String desc;

	/**
	* variable represents time of creation.
	**/
	private long created;

	/**
	* variable represents array of comments.
	**/
	private String[] comments;

	/**
	* Default constructor.
	**/
	public Item() {
	}

	/**
	* Constructor with parametrs.
	**/
	public Item(String name, String desc, long created) {
		this.name = name;
		this.desc = desc;
		this.created = created;
	}

	/**
	* Getter for id.
	**/
	public String getID() {
		return id;
	}

	/**
	* Setter for id.
	**/
	public void setID(String id) {
		this.id = id;
	}

	/**
	* Getter for name.
	**/
	public String getName() {
		return name;
	}

	/**
	* Setter for name.
	**/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Getter for description.
	**/
	public String getDescription() {
		return desc;
	}

	/**
	* Setter for description.
	**/
	public void setDescription(String desc) {
		this.desc = desc;
	}

	/**
	* Getter for time of creation.
	**/
	public long getCreated() {
		return created;
	}

	/**
	* Setter for time of creation.
	**/
	public void setCreated(long created) {
		this.created = created;
	}

	/**
	* Method for item output.
	* @return string representaion of object
	**/
	@Override
	public String toString() {
		return "id: " + id + ",  name: " + name + ", description: " + desc;
	}

	// TODO: getter and setter for "comments", equals() and hashCode()?
}