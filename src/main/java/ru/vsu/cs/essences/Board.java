package ru.vsu.cs.essences;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
/*

    private int fieldSize = 8;
    private Map<String, Cell> field = new HashMap<>();

    public Board(int fieldSize) {
        this.fieldSize = fieldSize;

    }

    public Map<String, Cell> getField() {
        return field;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public static String getStringCoordinates(int x, int y) {
        return x + " " + y;
    }

    private void setField() {
        for (int i = 0; i < fieldSize; i++) { // создаются все клетки
            for (int j = 0; j < fieldSize; j++){
                if((i+j)%2!=0) {
                    if(j==0 || j==fieldSize-1){
                        field.put(getStringCoordinates(i,j), new TransformCell());
                    }else {
                        field.put(getStringCoordinates(i,j), new Cell());
                    }
                }
            }
        }
    }
*/

    private Cell[][] field = new Cell[8][8];
    private Map<Checker, Cell> checkerToCell = new HashMap<>();
    private Map<Cell, Checker> cellToChecker = new HashMap<>();

    public Board() {
        Color color;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                }
                field[i][j] = new Cell(j, 7 - i, null, color);
            }
        }
    }




    public Cell[][] getField() {
        return field;
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
