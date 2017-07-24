package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки сортировки массива.
* @since 24/07/2017
* @version 1
**/
public class BubbleSortTest {

	/**
	* Метод тестирует сортировку.
	**/
	@Test
	public void testForBubbleSort() {
		int[] source = new int[] {5, 1, 2, 7, 3};
		BubbleSort bubbleSort = new BubbleSort();
		int[] resultArray = bubbleSort.sort(source);
		int[] expectedArray = new int[] {1, 2, 3, 5, 7};
		assertThat(resultArray, is(expectedArray));
	}
}