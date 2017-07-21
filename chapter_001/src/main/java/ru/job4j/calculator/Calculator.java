package ru.job4j.calculator;

/**
* Class Класс для осуществления арифметических операций.
* @since 21/07/2017
* @version 1
*/
public class Calculator {

/**
* В переменной сохраняется результат арифметических операций.
*/
	private double result;

/**
* Операция сложения.
* @param first первое слагаемое
* @param second второе слагаемое
*/
	public void add(double first, double second) {
		this.result = first + second;
	}

/**
* Операция вычитания.
* @param first уменьшаемое
* @param second вычитаемое
*/
	public void subtract(double first, double second) {
		this.result = first - second;
	}

/**
* Операция умножения.
* @param first умножаемое
* @param second множитель
*/
	public void multiplicate(double first, double second) {
		this.result = first * second;
	}

/**
* Операция деления.
* @param first делимое
* @param second делитель
*/
	public void divide(double first, double second) {
		this.result = first / second;
	}

/**
* Метод возвращает результат математической операции.
* @return результат операции
*/
	public double getResult() {
		return this.result;
	}
}