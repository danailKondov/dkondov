package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;

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
	private ArrayList<String> comments;

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
	 * Getter for comments.
	 **/
	public ArrayList<String> getComments() {
		return comments;
	}

	/**
	 * Setter for comments.
	 **/
	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}

	/**
	* Method for item output.
	* @return string representaion of object
	**/
	@Override
	public String toString() {
		String comment = null;
		if (comments != null) {
			comment = comments.toString();
		}
		return "id: " + id + ",  name: " + name + ", description: " + desc + ", created: " + created + " \n" +
				"comments: " + comment ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		if (getCreated() != item.getCreated()) return false;
		if (id != null ? !id.equals(item.id) : item.id != null) return false;
		if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) return false;
		return desc != null ? desc.equals(item.desc) : item.desc == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (getName() != null ? getName().hashCode() : 0);
		result = 31 * result + (desc != null ? desc.hashCode() : 0);
		result = 31 * result + (int) (getCreated() ^ (getCreated() >>> 32));
		return result;
	}
}