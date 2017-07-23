package ru.job4j.loop;

/**
* Class Представляет собой счетчик четных чисел.
* @since 23/07/2017
* @version 1
*/
public class Counter {

	/**
	* метод вычисляет сумму четных чисел в интервале.
	* @param start начало интервала
	* @param finish конец интервала
	* @return сумма
	**/
	public int add(int start, int finish) {
		int sum = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}