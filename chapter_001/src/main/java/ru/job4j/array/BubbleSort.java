package ru.job4j.array;

/**
* Класс сортирует массив пузырьковым методом.
* @since 24/07/2017
* @version 1
*/
public class BubbleSort {

	/**
	* Метод сортирует массив.
	* @param array исходный массив
	* @return возвращает отсортированный массив
	**/
	public int[] sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > i; j--) {
				if (array[j] < array[j - 1]) {
					int k = array[j - 1];
					array[j - 1] = array[j];
					array[j] = k;
				}
			}
		}
		return array;
	}
}