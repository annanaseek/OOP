package ru.vsu.cs.essences;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.*;

@JsonSerialize
@JsonDeserialize
public class Cell {

    private Coordinates coordinates;
    private Map<Link, Cell> linksFromCell = new HashMap<>();

    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Cell getCellFromLink(Link link) {
        return linksFromCell.get(link);
    }

    public void addLink(Link link, Cell cell){
        linksFromCell.put(link, cell);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonIgnore
    public List<Cell> getCellsFromLinks(){
        return new LinkedList<>(linksFromCell.values());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return coordinates.equals(cell.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates);
    }

    @Override
    public String toString() {
        return "Cell{" +
                "coordinates=" + coordinates +
                '}';
    }
}
