package ru.job4j.tracker;

import java.util.ArrayList;

/**
* Interface for input/output operations.
* @since 29/07/2017
* @version 1
*/
public interface Input {
	
	/**
	* Method for asking questions.
	**/
	String ask(String question);

	/**
	 * Method for asking questions with int return.
	 **/
	int ask(String question, ArrayList<Integer> range);
}