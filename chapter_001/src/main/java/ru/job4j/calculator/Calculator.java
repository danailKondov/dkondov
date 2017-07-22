package ru.job4j.calculator;

/**
* Class for arithmetic operations.
* @since 21/07/2017
* @version 1
*/
public class Calculator {

/**
* Result of operations.
*/
	private double result;

/**
* Operation of addition.
* @param first first argument
* @param second second argument
*/
	public void add(double first, double second) {
		this.result = first + second;
	}

/**
* Operation of subtraction.
* @param first first argument
* @param second second argument
*/
	public void subtract(double first, double second) {
		this.result = first - second;
	}

/**
* Operation of multiplication.
* @param first first argument
* @param second second argument
*/
	public void multiplicate(double first, double second) {
		this.result = first * second;
	}

/**
* Operation of division.
* @param first first argument
* @param second second argument
*/
	public void divide(double first, double second) {
		this.result = first / second;
	}

/**
* Method returns result of operations.
* @return result
*/
	public double getResult() {
		return this.result;
	}
}