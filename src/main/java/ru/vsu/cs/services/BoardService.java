package ru.vsu.cs.services;

import ru.vsu.cs.essences.Board;
import ru.vsu.cs.essences.Cell;
import ru.vsu.cs.essences.Checker;
import ru.vsu.cs.essences.Coordinates;

import java.util.*;


public class BoardService {
    /*public void fillBoard(Board board) {

        for (int i = 0; i < board.getField().length / 2 - 1; i++) {
            for (int j = 0; j < board.getField().length; j++) {
                if ((i + j) % 2 == 0) {
                    Checker ch = new Checker(true);
                    board.getCheckerToCell().put(ch, board.getField()[i][j]);
                    board.getCellToChecker().put(board.getField()[i][j], ch);
                } else {
                    board.getCellToChecker().put(board.getField()[i][j], null);
                }
            }
        }

        for (int i = board.getField().length / 2 - 1; i < board.getField().length / 2 + 1; i++) {
            for (int j = 0; j < board.getField().length; j++) {
                board.getCellToChecker().put(board.getField()[i][j], null);
            }
        }

        for (int i = board.getField().length / 2 + 1; i < board.getField().length; i++) {
            for (int j = 0; j < board.getField()[0].length; j++) {
                if ((i + j) % 2 == 0) {
                    Checker ch = new Checker(false);
                    board.getCheckerToCell().put(ch, board.getField()[i][j]);
                    board.getCellToChecker().put(board.getField()[i][j], ch);
                } else {
                    board.getCellToChecker().put(board.getField()[i][j], null);
                }
            }
        }
    }
*/

    public void fillBoard(Board board){
        placeCheckers(board, true, new Coordinates(0, 0));
        placeCheckers(board, false, new Coordinates(7, 7));
    }

    private void placeCheckers(Board board, boolean isWhite, Coordinates startCoordinates){
        Set<Cell> visited = new HashSet<>();
        Stack<Cell> stack = new Stack<>();
        stack.push(board.getCell(startCoordinates));
        visited.add(null);

        while (!stack.empty()){
            Cell cell = stack.pop();
            if (!visited.contains(cell)){
                visited.add(cell);
                if(isCellInsideBorders(cell.getCoordinates(), isWhite)){
                    Checker checker = new Checker(isWhite);
                    board.addCellAndChecker(cell, checker);
                }
                cell.getCellsFromLinks().forEach(stack::push);
            }
        }
    }

    private boolean isCellInsideBorders(Coordinates coordinates, boolean isWhite){
        if(isWhite) {
            return coordinates.getX() < 8 &&
                    coordinates.getY() < 3 &&
                    coordinates.getX() >= 0 &&
                    coordinates.getY() >= 0;
        } else {
            return coordinates.getX() < 8 &&
                    coordinates.getY() < 8 &&
                    coordinates.getX() >= 0 &&
                    coordinates.getY() >= 5;
        }
    }

}
