package ru.job4j.condition;

/**
* Class Представляет собой точку с координатами.
* @since 22/07/2017
* @version 1
*/
public class Point {

	/**
	* координаты X.
	**/
	private int x;

	/**
	* координаты Y.
	**/
	private int y;

	/**
	* конструктор класса.
	* @param x координата точки X
	* @param y координата точки Y
	**/
	public  Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	* геттер Х.
	* @return значение координаты Х
	**/
	public int getX() {
		return this.x;
	}

	/**
	* геттер Y.
	* @return значение координаты Y
	**/
	public int getY() {
		return this.y;
	}

	/**
	* Метод определяет нахождение точки на заданной функции.
	* @param a координата проверяемой точки
	* @param b координата проверяемой точки
	* @return логичекое значение
	**/
	public boolean is(int a, int b) {
		return this.y == a * this.x + b;
	}
}