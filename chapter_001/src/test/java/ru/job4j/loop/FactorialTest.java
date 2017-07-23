package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки счетчика факториала.
* @since 23/07/2017
* @version 1
**/
public class FactorialTest {

	/**
	* Метод тестирует счетчик четных чисел.
	**/
	@Test
	public void whenCountFactorial() {
		Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		assertThat(result, is(120));

		result = factorial.calc(0);
		assertThat(result, is(1));
	}
}