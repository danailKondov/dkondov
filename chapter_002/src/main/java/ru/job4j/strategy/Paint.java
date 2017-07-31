package ru.job4j.strategy;


/**
* Class for draw geometric shapes.
* @since 31/07/2017
* @version 1
*/
public class Paint {

	/**
	* shape.
	**/
	private Shape shape;

	/**
	* Constructor with parameters.
	* @param shape - define shape to draw
	**/
	public Paint(Shape shape) {
		this.shape = shape;
	}

	/**
	* Method for drawing shapes.
	* @param shape - define shape to draw
	**/
	public void draw() {
		System.out.println(shape.pic());
	}
	
	/**
	* Method for drawing shapes.
	* @param shape - define shape to draw
	**/
	public void draw(Shape shape) {
		System.out.println(shape.pic());
	}

	/**
	* Setter for shape.
	* @param shape - define shape to draw
	**/
	public void setShape(Shape shape) {
		this.shape = shape;
	}
}