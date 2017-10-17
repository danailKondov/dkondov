package ru.job4j.bombergame;

/**
 * Enumeration of moving direction.
 *
 * @since 15/10/2017
 * @version 1
 */
public enum Direction {
    UP (-1, 0),
    DOWN (1, 0),
    LEFT (0, -1),
    RIGHT (-1, 0);

    private final int y;
    private final int x;

    Direction(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
