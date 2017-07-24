package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки удаления дубликатов из массива.
* @since 24/07/2017
* @version 1
**/
public class ArrayDuplicateTest {

	/**
	* Метод тестирует удаление дубликатов.
	**/
	@Test
	public void testArrayDuplicateRemoval() {
		ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		String[] arraySource = {"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] resultArray = arrayDuplicate.remove(arraySource);
		String[] expectArray = {"Привет", "Мир", "Супер"};
		assertThat(resultArray, is(expectArray));
	}
}