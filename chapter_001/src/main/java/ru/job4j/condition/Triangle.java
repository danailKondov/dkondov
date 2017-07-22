package ru.job4j.condition;


/**
* Class Представляет собой треугольник.
* @since 22/07/2017
* @version 1
*/
public class Triangle {

	/**
	* точка a.
	**/
	private Point a;

	/**
	* точка b.
	**/
	private Point b;

	/**
	* точка c.
	**/
	private Point c;

	/**
	* конструктор класса.
	* @param a точка a
	* @param b точка b
	* @param c точка c
	**/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	* метод вычисляет площадь треугольника.
	* @return площадь
	**/
	public double area() {

		// проверка на условие возможности: нахождение всех точек на линии
		if (a.is(b.getX(), b.getY()) && b.is(c.getX(), c.getY())) {
			return 0D;
		}

		// Вычисляем по формуле Герона через полупериметр
		double ab = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
		double ac = Math.sqrt(Math.pow(a.getX() - c.getX(), 2) + Math.pow(a.getY() - c.getY(), 2));
		double bc = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
		double halfPerimetr = (ab + ac + bc) / 2;
		double area = Math.sqrt(halfPerimetr * (halfPerimetr - ab) * (halfPerimetr - ac) * (halfPerimetr - bc));

		return area;
	}
}