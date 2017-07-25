package ru.job4j.loop;

/**
* Класс вычисляет факториал.
* @since 23/07/2017
* @version 1
*/
public class Factorial {

	/**
	* метод вычисляет факториал.
	* @param n число, для которого вычисляется факториал
	* @return значение факториала
	**/
	public int calc(int n) {
		int result = 1;		
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}