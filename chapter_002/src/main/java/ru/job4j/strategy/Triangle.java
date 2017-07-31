package ru.job4j.strategy;


/**
* Class for geometric shape triangle.
* @since 31/07/2017
* @version 1
*/
public class Triangle implements Shape {

	/**
	* Method returns string representation of shape.
	* @returns string representation of shape
	**/
	public String pic() {
		StringBuilder sb = new StringBuilder();
		String sep = System.getProperty("line.separator");
		sb.append("       ").append(sep)
		  .append("   |   ").append(sep)
		  .append("  | |  ").append(sep)
		  .append(" |   | ").append(sep)
		  .append("|     |").append(sep)
		  .append("-------").append(sep);
		return sb.toString();
	}
}