package ru.job4j.tracker;

import java.util.*;

/**
* Class for input/output operations.
* @since 29/07/2017
* @version 1
*/
public class ConsoleInput implements Input {

	/**
	* Method for asking questions to user.
	* @param question
	* @return answer
	**/
	public String ask(String question) {
		System.out.println(question);
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}