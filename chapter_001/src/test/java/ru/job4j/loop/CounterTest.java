package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки счетчика четных чисел.
* @since 23/07/2017
* @version 1
**/
public class CounterTest {

	/**
	* Метод тестирует счетчик четных чисел.
	**/
	@Test
	public void whenCountEvenNumbers() {
		Counter count = new Counter();
		int result = count.add(1, 10);
		assertThat(result, is(30));
	}
}