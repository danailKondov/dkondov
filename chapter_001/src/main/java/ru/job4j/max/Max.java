package ru.job4j.max;

/**
* Class для осуществления сравнения простых чисел.
* @since 22/07/2017
* @version 1
*/
public class Max {

/**
* Операция сравнения.
* @param first первое число для сравнения
* @param second второе число для сравнения
* @return возвращает наибольшее из двух чисел
*/
	public int max(int first, int second) {
		int result = (first > second) ? first : second;
		return result;
	}

/**
* Операция сравнения.
* @param first первое число для сравнения
* @param second второе число для сравнения
* @param third третье число для сравнения
* @return возвращает наибольшее из трех чисел
*/
	public int max(int first, int second, int third) {
		return max(max(first, second), third);
	}
}