package ru.job4j.tracker;

import java.util.*;

/**
* Class for input/output operations.
* @since 04/08/2017
* @version 2
*/
public class ConsoleInput implements Input {

	/**
	* Method for asking questions to user.
	* @param question - next of question
	* @return answer - answer to question
	**/
	public String ask(String question) {
		System.out.println(question);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	/**
	 * Method for asking menu options.
	 * @param question - text of question
	 * @param range - range of answers
	 * @return number of menu option
	 */
	@Override
	public int ask(String question, int[] range) {
		int key = Integer.valueOf(ask(question));
		for (int value :
				range) {
			if (value == key) {
				return key;
			}
		}
		throw new MenuOutException("Out of menu range");
	}
}