package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class ����� ��� �������� �����.
* @since 21/07/2017
* @version 1
**/
public class PointTest {

	/**
	* ����� ��������� �������� �������� ���������� ����� �� �������.
	**/
	@Test
	public void whenPointMeetFunction() {
		Point point = new Point(2, 4);
		boolean result = point.is(2, 1);
		assertThat(result, is(true));
	}
}