package ru.job4j.controlquestion001;

/**
* Класс производит поиск подстроки в строке.
* @since 24/07/2017
* @version 1
*/
public class StringSearcher {
	
	/**
	* метод проверяет наличие подстроки в строке.
	* @param origin строка
	* @param sub подстрока
	* @return результат проверки
	**/
	public boolean contains(String origin, String sub) {
		boolean result = false;
		char[] originArray = origin.toCharArray();
		char[] subArray = sub.toCharArray();
		for (int i = 0; i < originArray.length; i++) {
			if (originArray[i] == subArray[0] && (i + subArray.length) <= originArray.length) {
				for (int j = 1; j < subArray.length; j++) {
					if (subArray[j] != originArray[i + j]) {
						break;
					}
					if (j == subArray.length - 1) {
						result = true;
					}
				}
			}
		}
		return result;
	}
}