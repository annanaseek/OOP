package ru.vsu.cs.essences;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Cell {

    Coordinates coordinates;
    ArrayList links = new ArrayList<Cell>();

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Cell(Coordinates coordinates, ArrayList links) {
        this.coordinates = coordinates;
        this.links = links;
    }

    public void setLink (int index, Cell link) {
        links.set(index, link);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(coordinates, cell.coordinates) &&
                Objects.equals(links, cell.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, links);
    }
}
