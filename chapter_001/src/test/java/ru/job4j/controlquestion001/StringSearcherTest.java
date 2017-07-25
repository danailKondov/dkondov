package ru.job4j.controlquestion001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки поиска подстроки в строке.
* @since 25/07/2017
* @version 1
**/
public class StringSearcherTest {

	/**
	* Метод тестирует поиск подстроки.
	**/
	@Test
	public void testStringContainsSubstring() {
		StringSearcher stringSearcher = new StringSearcher();
		boolean result = stringSearcher.contains("Привет", "иве");
		assertThat(result, is(true));
	}
}