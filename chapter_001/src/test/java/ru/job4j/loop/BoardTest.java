package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class Класс для проверки рисования доски в пседографике.
* @since 23/07/2017
* @version 1
**/
public class BoardTest {

	/**
	* Метод тестирует доску 3х3.
	**/
	@Test
	public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
		Board board = new Board();
		String result = board.paint(3, 3);
		final String line = System.getProperty("line.separator");
		String expected = String.format("X X%s X %sX X%s", line, line, line);
		assertThat(result, is(expected));
	}

	/**
	* Метод тестирует доску 5х4.
	**/
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        //напишите здесь тест, проверяющий формирование доски 5 на 4.
		Board board = new Board();
		String result = board.paint(5, 4);
		final String line = System.getProperty("line.separator");
		String expected = String.format("X X X%s X X %sX X X%s X X %s", line, line, line, line);
		assertThat(result, is(expected));
	}
}