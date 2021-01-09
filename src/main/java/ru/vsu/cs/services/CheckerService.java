package ru.vsu.cs.services;

import ru.vsu.cs.essences.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckerService {
    public CheckerService() {
    }

    public List<Cell> getAvailableCells(Board board, Checker checker) {
        List<Cell> list = new LinkedList<>();
        Cell cell;
        if (checker.isQueen()) {
            for (Link link : Link.values()) {
                Link correctLink = getCorrectLink(link, checker);
                cell = board.getCell(checker).getCellFromLink(correctLink);
                while (cell != null && board.getChecker(cell) == null) {
                    list.add(cell);
                    cell = cell.getCellFromLink(correctLink);
                }
            }
        } else {
            cell = getNextCell(board, checker, Link.LEFT_UP);
            if (cell != null && board.getChecker(cell) == null) {
                list.add(cell);
            }
            cell = getNextCell(board, checker, Link.RIGHT_UP);
            if (cell != null && board.getChecker(cell) == null) {
                list.add(cell);
            }
        }
        return list;
    }

    /**
     *
     * @return мапу конечной клетки после атаки и шашки, которая атакуется
     */
    public Map<Cell, Checker> getAttackableCells(Board board, Checker checker) {
        Map<Cell, Checker> attack = new HashMap<>();
        Cell cell;
        Checker attackedChecker;
        for (Link link : Link.values()) {
            Link correctLink = getCorrectLink(link, checker);
            cell = board.getCell(checker);
            do {
                cell = cell.getCellFromLink(correctLink);
                attackedChecker = board.getChecker(cell);
            } while (checker.isQueen() && cell != null && attackedChecker == null);
            if (cell != null && attackedChecker != null && attackedChecker.isWhite() != checker.isWhite()) {
                cell = cell.getCellFromLink(correctLink);
                if (cell != null && board.getChecker(cell) == null) {
                    attack.put(cell, attackedChecker);
                }
            }
        }
        return attack;
    }

    public void doAttack(Board board, Checker currentChecker, Cell endCell, Checker attackedChecker) {
        doStep(board, currentChecker, endCell);

        board.removeCellToChecker(board.getCell(attackedChecker));
        board.removeCheckerToCell(attackedChecker);
    }

    public void doStep(Board board, Checker checker, Cell endCell) {
        board.removeCellToChecker(board.getCell(checker));
        board.removeCheckerToCell(checker);

        board.putCheckerToCell(checker, endCell);
        board.putCellToChecker(endCell, checker);

        if(checker.isWhite() && endCell.getCoordinates().getY() == board.FIELD_SIZE - 1) {
            checker.setQueen(true);
        }

        if(!checker.isWhite() && endCell.getCoordinates().getY() == 0) {
            checker.setQueen(true);
        }
    }

    private Cell getNextCell(Board board, Checker checker, Link link) {
        Cell cell = board.getCell(checker);
        Link correctLink = getCorrectLink(link, checker);
        return cell.getCellFromLink(correctLink);
    }

    private Link getCorrectLink(Link link, Checker checker) {
        if (!checker.isWhite()) {
            switch (link) {
                case LEFT_UP:
                    return Link.RIGHT_DOWN;
                case RIGHT_UP:
                    return Link.LEFT_DOWN;
                case LEFT_DOWN:
                    return Link.RIGHT_UP;
                case RIGHT_DOWN:
                    return Link.LEFT_UP;
            }
        }
        return link;
    }


}
