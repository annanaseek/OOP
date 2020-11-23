package ru.vsu.cs.essences;

import java.awt.*;

public class Cell {

    final int x;
    final int y;
    final Color color;

    public Cell(int x, int y, Checker checker, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}
