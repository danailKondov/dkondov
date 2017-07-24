package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки инверсии массива.
* @since 24/07/2017
* @version 1
**/
public class TurnTest {

	/**
	* Метод тестирует инверсию массива с нечетной длиной.
	**/
	@Test
	public void testInversionArrayLengthFive() {
		int[] source = new int[] {1, 2, 3, 4, 5};
		Turn turn = new Turn();
		int[] resultArray = turn.back(source);
		int[] expectedArray = new int[] {5, 4, 3, 2, 1};
		assertThat(resultArray, is(expectedArray));
	}

	/**
	* Метод тестирует инверсию массива с четной длиной.
	**/
	@Test
	public void testInversionArrayLengthFour() {
		int[] source = new int[] {4, 1, 6, 2};
		Turn turn = new Turn();
		int[] resultArray = turn.back(source);
		int[] expectedArray = new int[] {2, 6, 1, 4};
		assertThat(resultArray, is(expectedArray));
	}
}