package ru.job4j.loop;

/**
* Класс создает пирамиду в псевдографике.
* @since 23/07/2017
* @version 1
*/
public class Paint {

	/**
	* Метод рисует пирамиду в псевдографике.
	* @param h высота
	* @return возврашает пирамиду в виде строки
	**/
	public String piramid(int h) {
		StringBuilder sb = new StringBuilder();
		int osnvanie = 1 + ((h - 1) * 2);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < osnvanie; j++) {
				int seredina = osnvanie / 2;
				if ((j >= seredina - i) && (j <= seredina + i)) {
					sb.append("^");
				} else {
					sb.append(" ");
				}				
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}