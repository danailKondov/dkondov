package ru.job4j.strategy;

import org.junit.Test;
import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Paint class.
* @since 31/07/2017
* @version 1
**/
public class PaintTest {

	/**
	* Method for testing square shape draw.
	**/
	@Test
	public void testOfSquareShapeDraw() {
		Shape square = new Square();
		Paint paint = new Paint(square);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		paint.draw();
		String sep = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("-------").append(sep)
		  .append("|     |").append(sep)
		  .append("|     |").append(sep)
		  .append("|     |").append(sep)
		  .append("|     |").append(sep)
		  .append("-------").append(sep).append(sep);
		String expected = sb.toString();
		String	result = out.toString();
		assertThat(result, is(expected));
	}

	/**
	* Method for testing triangle shape draw.
	**/
	@Test
	public void testOfTriangleShapeDraw() {
		Shape triangle = new Triangle();
		Paint paint = new Paint(triangle);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		paint.draw();
		String sep = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();
		sb.append("       ").append(sep)
		  .append("   |   ").append(sep)
		  .append("  | |  ").append(sep)
		  .append(" |   | ").append(sep)
		  .append("|     |").append(sep)
		  .append("-------").append(sep).append(sep);
		String expected = sb.toString();
		String	result = out.toString();
		assertThat(result, is(expected));
	}
}