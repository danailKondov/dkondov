package ru.job4j.tracker;


/**
* Interface for all user actions.
* @since 02/07/2017
* @version 1
*/
public interface UserAction {

	/**
	* Action identificator.
	* @returns action id
	**/
	int key();

	/**
	* Method execute action.
	* @param input - class, which implements Input interface
	* @param tracker - tracker
	**/
	void execute(Input input, Tracker tracker);

	/**
	* Information about action.
	* @returns info about action
	**/
	String info();
}