package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки поиска максимального из двух чисел.
* @since 21/07/2017
* @version 1
**/
public class MaxTest {

	/**
	* Метод проверяет операцию сравнения.
	**/
	@Test
	public void whenFirstLessSecond() {
		Max maxim = new Max();
		int result = maxim.max(1, 2);
		assertThat(result, is(2));
	}

	/**
	* Метод проверяет операцию сравнения с тремя числами.
	**/
	@Test
	public void whenFirstLessSecondAndThird() {
		Max maxim = new Max();
		int result = maxim.max(1, 2, 3);
		assertThat(result, is(3));
	}
}