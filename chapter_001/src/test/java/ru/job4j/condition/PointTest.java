package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Для проверки точки.
* @since 21/07/2017
* @version 1
**/
public class PointTest {

	/**
	* Метод тестирует операцию проверки нахождения точки на функции.
	**/
	@Test
	public void whenPointMeetFunction() {
		Point point = new Point(2, 4);
		boolean result = point.is(1, 2);
		assertThat(result, is(true));
	}
}