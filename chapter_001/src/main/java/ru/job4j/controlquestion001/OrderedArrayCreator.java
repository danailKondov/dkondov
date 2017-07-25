package ru.job4j.controlquestion001;

/**
* Класс производит сложение двух упорядоченных по возрастанию массивов в третий.
* @since 25/07/2017
* @version 1
*/
public class OrderedArrayCreator {

	/**
	* метод проверяет складывает два упорядоченных массива.
	* @param firstArray первый массив
	* @param secondArray второй массив
	* @return результирующий массив
	**/
	public int[] createArray(int[] firstArray, int[] secondArray) {
		int[] newArray = new int[firstArray.length + secondArray.length];
		int k = 0;
		int i = 0;
		int j = 0;

		// сравниваем элементы двух массивов и наименьший записываем
		// в новый массив
		while (i < firstArray.length) {
			while (j < secondArray.length) {
				if (firstArray[i] <= secondArray[j]) {
					newArray[k] = firstArray[i];
					k++;
					i++;
					break;
				} else {
					newArray[k] = secondArray[j];
					k++;
					j++;
					break;
				}
			}
			// если один массив закончился, просто добавляем 
			// оставшиеся значения из другого массива
			if (j == secondArray.length) {
				while(i < firstArray.length) {
					newArray[k] = firstArray[i];
					k++;
					i++;
				}
			} else if (i == firstArray.length) {
				while(j < secondArray.length) {
					newArray[k] = secondArray[j];
					k++;
					j++;
				}
			}
		}
		return newArray;
	}
}