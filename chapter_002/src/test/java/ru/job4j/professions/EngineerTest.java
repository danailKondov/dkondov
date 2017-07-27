package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Engineer class.
* @since 27/07/2017
* @version 1
**/
public class EngineerTest {

	/**
	* Method for testing Teacher class
	**/
	@Test
	public void TestEngineerClass() {
		Engineer engineer = new Engineer(45, "Mihail Sergeevich", 45000, 20, "DRSU 56");
		String result = engineer.work();
		String expected = "Mihail Sergeevich is working in DRSU 56";
		assertThat(result, is(expected));
	}
}