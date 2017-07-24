package ru.job4j.array;

/**
* Class Класс для вращения многомерного массива.
* @since 24/07/2017
* @version 1
**/
public class RotateArray {

	/**
	* Метод поворачивает квадратный массив.
	* @param array исходный массив
	* @return возвращает новый массив
	**/
	public int[][] rotate(int[][] array) {
		int[][] result = new int[array.length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				result[j][array.length - 1 - i] = array[i][j];
			}
		}
		return result;
	}
}
