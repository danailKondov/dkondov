package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки рисования доски в пседографике.
* @since 23/07/2017
* @version 1
**/
public class PaintTest {

	/**
	* Метод тестирует пирамиду высотой 2.
	**/
	@Test
	public void piramidTestHightTwo() {
		Paint paint = new Paint();
        String result = paint.piramid(2);
		final String separator = System.getProperty("line.separator");
        String expected = String.format(" ^ %s^^^%s", separator, separator);
        assertThat(result, is(expected));
	}

	/**
	* Метод тестирует пирамиду высотой 3.
	**/
	@Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
       //напишите здесь тест, проверяющий формирование пирамиды для высоты 3.
	   Paint paint = new Paint();
        String result = paint.piramid(3);
		final String separator = System.getProperty("line.separator");
        String expected = String.format("  ^  %s ^^^ %s^^^^^%s", separator, separator, separator);
        assertThat(result, is(expected));
    }
}