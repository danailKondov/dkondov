package ru.job4j.chess;

/**
 * Cell is element of the Board.
 * @since 05/08/2017
 * @version 1
 */
public class Cell {

    /**
     * X coordinate of cell.
     */
    private int x;

    /**
     * Y coordinate of cell.
     */
    private int y;

    /**
     * Constructor with parameters.
     * @param x - X coordinate
     * @param y - Y coordinate
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method returns position in proper chess notification.
     * @return - cell's position
     */
    public String getPosition() {
        int[] height = {8, 7, 6, 5, 4, 3, 2, 1};
        String[] width = {"a", "b", "c", "d", "e", "f", "g", "h"};
        return width[x] + height[y];
    }

    /**
     * Getter for X.
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for Y.
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * String representation of the object.
     * @return - position
     */
    @Override
    public String toString() {
        return getPosition();
    }

    /**
     * Method needed for proper comparison of objects.
     * @param o - Cell to compare
     * @return result of comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;
    }

    /**
     * Method computes hash code.
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
