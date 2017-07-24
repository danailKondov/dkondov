package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки вращения массива.
* @since 24/07/2017
* @version 1
**/
public class RotateArrayTest {

	/**
	* Метод тестирует вращение массива 2х2.
	**/
	@Test
	public void testRotateSquareArraySizeTwo() {
		RotateArray rotateArray = new RotateArray();
		int[][] source = {
			{1, 2},
			{4, 5}
		};
		int[][] expect = {
			{4, 1},
			{5, 2}
		};
		int[][] result = rotateArray.rotate(source);
		assertThat(result, is(expect));
	}

	/**
	* Метод тестирует вращение массива 3х3.
	**/
	@Test
	public void testRotateSquareArraySizeThree() {
		RotateArray rotateArray = new RotateArray();
		int[][] source = {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}
		};
		int[][] expect = {
			{7, 4, 1},
			{8, 5, 2},
			{9, 6, 3}
		};
		int[][] result = rotateArray.rotate(source);
		assertThat(result, is(expect));
	}
}