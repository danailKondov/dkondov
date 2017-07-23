package ru.job4j.loop;

/**
* Класс создает шахматную доску в псевдографике.
* @since 23/07/2017
* @version 1
*/
public class Board {

	/**
	* метод создает доску.
	* @param width ширина доски
	* @param height высота доски
	* @return строка с доской
	**/
	public String paint(int width, int height) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						sb.append("X");
					} else {
						sb.append(" ");
					}
				} else {
					if (j % 2 == 0) {
						sb.append(" ");
					} else {
						sb.append("X");
					}
				}
				if (j == width - 1) {
					sb.append(System.getProperty("line.separator"));
				}
			}
		}
		return sb.toString();
	}
}