package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Profession class.
* @since 27/07/2017
* @version 1
**/
public class ProfessionTest {
	
	/**
	* Method for testing Profession class
	**/
	@Test
	public void TestProfessionClass() {
		Profession profession = new Profession(45, "Professional", 15000);
		String result = profession.sleep(6) + " after " + profession.eat() + " then " + 
		profession.goToWork() + " and " + profession.goHome();
		String expected = "Professional is sleeping for 6 hours" + 
		" after Professional is eating then Professional is going to work and Professional is going home";
		assertThat(result, is(expected));
	}
}