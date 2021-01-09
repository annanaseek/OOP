package ru.vsu.cs.essences;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonSerialize
public class Board {

    public final int FIELD_SIZE = 8;
    private Map<Checker, Cell> checkerToCell = new HashMap<>();
    @JsonSerialize
    private Map<Cell, Checker> cellToChecker = new HashMap<>();
    private Map<Coordinates, Cell> field = new HashMap<>();

    public Board() {
        createCell(0, 0);
    }

    private Cell createCell(int x, int y) {
        if (x < 0 || x >= FIELD_SIZE || y < 0 || y >= FIELD_SIZE) {
            return null;
        }
        Coordinates currentCoordinates = new Coordinates(x, y);
        if (field.containsKey(currentCoordinates)) {
            return field.get(currentCoordinates);
        }

        Cell cell = new Cell(currentCoordinates);
        field.put(currentCoordinates, cell);

        cell.addLink(Link.LEFT_UP, createCell(x - 1, y + 1));
        cell.addLink(Link.RIGHT_UP, createCell(x + 1, y + 1));
        cell.addLink(Link.RIGHT_DOWN, createCell(x + 1, y - 1));
        cell.addLink(Link.LEFT_DOWN, createCell(x - 1, y - 1));

        return cell;

    }

    public Cell getCell(Coordinates coordinates){
        return field.get(coordinates);
    }

    public void removeCellToChecker(Cell cell) {
        cellToChecker.remove(cell);
    }

    public void removeCheckerToCell(Checker checker) {
        checkerToCell.remove(checker);
    }

    public void putCheckerToCell(Checker checker, Cell cell){
        checkerToCell.put(checker, cell);
    }

    public void putCellToChecker(Cell cell, Checker checker){
        cellToChecker.put(cell, checker);
    }

    public void addCellAndChecker(Cell cell, Checker checker){
        cellToChecker.put(cell, checker);
        checkerToCell.put(checker, cell);
    }

    public Checker getChecker(Cell cell) {
        return cellToChecker.get(cell);
    }

    public Cell getCell(Checker checker) {
        return checkerToCell.get(checker);
    }

    public List<Checker> getCheckersByColor(boolean isWhite) {
        return checkerToCell.keySet().stream().filter(c -> c.isWhite() == isWhite).collect(Collectors.toList());
    }

    public void setCellToChecker(Map<Cell, Checker> cellToChecker) {
        this.cellToChecker = cellToChecker;
    }
}
