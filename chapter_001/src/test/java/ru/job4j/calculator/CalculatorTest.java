package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class For testing calculation operations.
* @since 21/07/2017
* @version 1
**/
public class CalculatorTest {

	/**
	* Testing addition.
	**/
	@Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

	/**
	* Testing subtraction.
	**/
	@Test
    public void whenOneMinusOneThenZero() {
        Calculator calc = new Calculator();
        calc.subtract(1D, 1D);
        double result = calc.getResult();
        double expected = 0D;
        assertThat(result, is(expected));
    }

	/**
	* Testing multiplication.
	**/
	@Test
    public void whenOneMultiplicateThreeThenThree() {
        Calculator calc = new Calculator();
        calc.multiplicate(1D, 3D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

	/**
	* Testing division.
	**/
	@Test
    public void whenTwentyDivideFiveThenFour() {
        Calculator calc = new Calculator();
        calc.divide(20D, 5D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }
}