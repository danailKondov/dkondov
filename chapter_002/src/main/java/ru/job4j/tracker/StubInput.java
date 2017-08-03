package ru.job4j.tracker;


/**
* Class for input/output testing.
* @since 30/07/2017
* @version 1
*/
public class StubInput implements Input {

	/**
	* Test answers.
	**/
	private String[] answers;

	/**
	* Index of answers.
	**/
	private int position = 0;

	/**
	* Constructor with parameters.
	* @param answers is array of test answers
	**/
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	* Method for simulating asking questions to user.
	* @param question - question
	* @return answer
	**/
	public String ask(String question) {
		return answers[position++];
	}

	/**
	 * Method for simulating asking questions to user.
	 * @param question - question
	 * @param range - range
	 * @return number of option
	 */
	@Override
	public int ask(String question, int[] range) {
		return Integer.valueOf(answers[position++]);
	}
}