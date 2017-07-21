package ru.job4j.calculator;

/**
* Class ����� ��� ������������� �������������� ��������.
* @since 21/07/2017
* @version 1
*/
public class Calculator {

/**
* � ���������� ����������� ��������� �������������� ��������.
*/
	private double result;

/**
* �������� ��������.
* @param first ������ ���������
* @param second ������ ���������
*/
	public void add(double first, double second) {
		this.result = first + second;
	}

/**
* �������� ���������.
* @param first �����������
* @param second ����������
*/
	public void subtract(double first, double second) {
		this.result = first - second;
	}

/**
* �������� ���������.
* @param first ����������
* @param second ���������
*/
	public void multiplicate(double first, double second) {
		this.result = first * second;
	}

/**
* �������� �������.
* @param first �������
* @param second ��������
*/
	public void divide(double first, double second) {
		this.result = first / second;
	}

/**
* ����� ���������� ��������� �������������� ��������.
* @return ��������� ��������
*/
	public double getResult() {
		return this.result;
	}
}