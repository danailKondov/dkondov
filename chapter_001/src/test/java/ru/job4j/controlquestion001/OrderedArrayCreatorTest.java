package ru.job4j.controlquestion001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки создания одного упорядоченного массива из двух.
* @since 25/07/2017
* @version 1
**/
public class OrderedArrayCreatorTest {

	/**
	* Метод тестирует создание массива.
	**/
	@Test
	public void TestArrayCreation() {
		OrderedArrayCreator orderedCreator = new OrderedArrayCreator();
		int[] firstArray = {1, 3, 5, 7, 10, 20, 25};
		int[] secondArray = {3, 7, 8, 9, 10, 11, 12};
		int[] result = orderedCreator.createArray(firstArray, secondArray);
		int[] expected = {1, 3, 3, 5, 7, 7, 8, 9, 10, 10, 11, 12, 20, 25};
		assertThat(result, is(expected));
	}
}