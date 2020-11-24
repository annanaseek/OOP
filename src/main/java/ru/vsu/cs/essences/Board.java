package ru.vsu.cs.essences;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {

    final int fieldSize = 8;
    Map<Coordinates, Cell> field;
    private Map<Checker, Cell> checkerToCell = new HashMap<>();
    private Map<Cell, Checker> cellToChecker = new HashMap<>();

    public Board() {
        createCell(0, 0);
        fillField();
    }

    private Cell createCell(int x, int y) {
        if (x < 0 || y >= fieldSize || y < 0 || y >= fieldSize) {
            return null;
        }
        Coordinates currentCoordinates = new Coordinates(x, y);
        if (field.containsKey(currentCoordinates)) {
            return field.get(currentCoordinates);
        }

        Cell cell = new Cell(currentCoordinates);
        field.put(currentCoordinates, cell);

        cell.setLink(0, new Cell(new Coordinates(x - 2, y + 2)));
        cell.setLink(1, new Cell(new Coordinates(x + 2, y + 2)));
        cell.setLink(2, new Cell(new Coordinates(x + 2, y - 2)));
        cell.setLink(3, new Cell(new Coordinates(x - 2, y - 2)));

        return cell;

    }

    private void fillField () {

    }


    public Map<Checker, Cell> getCheckerToCell() {
        return checkerToCell;
    }

    public Map<Cell, Checker> getCellToChecker() {
        return cellToChecker;
    }

    public void setCheckerToCell(Map<Checker, Cell> checkerToCell) {
        this.checkerToCell = checkerToCell;
    }

    public void setCellToChecker(Map<Cell, Checker> cellToChecker) {
        this.cellToChecker = cellToChecker;
    }
}
