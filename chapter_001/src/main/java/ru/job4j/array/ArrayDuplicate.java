package ru.job4j.array;

import java.util.Arrays;

/**
* Class Класс для удаления дубликатов из массива.
* @since 24/07/2017
* @version 1
**/
public class ArrayDuplicate {

	/**
	* Метод удаляет дубликаты из массива.
	* @param array исходный массив
	* @return возвращает новый массив
	**/
	public String[] remove(String[] array) {
		// переменная к - ограничитель поиска, растет по мере накопления
		// результатов поиска в конце списка
		int k = 0;
		for (int i = 0; i < array.length - 1 - k; i++) {
			for (int j = i + 1; j < array.length - k;) {
				if (array[i].equals(array[j])) {
					String s = array[j];
					array[j] = array[array.length - 1 - k];
					array[array.length - 1 - k] = s;
					k++;
				}
				// инкремент вынесен из объявления цикла для того, чтобы
				// не переводить поиск на следующтй элемент после нахождения
				// совпадения, поскольку элемент, скопированный из конца
				// списка так же может быть дубликатом.
				j++;
			}
		}
		array = Arrays.copyOf(array, array.length - k);
		return array;
	}
}