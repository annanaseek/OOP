package ru.vsu.cs.services;

import ru.vsu.cs.essences.Board;
import ru.vsu.cs.essences.Cell;
import ru.vsu.cs.essences.Player;

import java.awt.*;

public class CheckerService {
    public CheckerService() {
    }

    public boolean canDoStep(Cell c1, Cell c2, Player player, Board board) {
        int ficha = 1;
        if (player.isFirst()) {
            ficha = -1;
        }

        if (board.getCellToChecker().get(c1) == null) {
            System.out.println("исходная клетка пуста");
            return false;
        }

        if (player.isFirst() && !board.getCellToChecker().get(c1).isWhite() || !player.isFirst() &&
                board.getCellToChecker().get(c1).isWhite()) {
            System.out.println("это не твоя шашка, твои с другим номером");
            return false;
        }

        if (board.getCellToChecker().get(c2) != null) {
            System.out.println("туда нельзя ходить-там занято");
            return false;
        }

        if (c2.getX() - c1.getX() == ficha && Math.abs(c2.getY() - c1.getY()) == 1) {
            return true;
        }
        if (Math.abs(c2.getX() - c1.getX()) == 2 && Math.abs(c2.getY() - c1.getY()) == 2) {
            int dx = c1.getX() + (c2.getX() - c1.getX()) / 2;
            int dy = c1.getY() + (c2.getY() - c1.getY()) / 2;
            Cell c = board.getField()[dx][dy];
            if (board.getCellToChecker().get(c) != null && (player.isFirst() && !board.getCellToChecker().get(c).isWhite() ||
                    !player.isFirst() && board.getCellToChecker().get(c).isWhite())) {
                return true;
            }
        }
        System.out.println("шашки так не ходят");
        return false;

    }

    public void doStep(Cell c1, Cell c2, Board board) {

        board.getCheckerToCell().put(board.getCellToChecker().get(c1), c2);
        board.getCellToChecker().put(c2, board.getCellToChecker().get(c1));
        board.getCellToChecker().put(c1, null);


        if (Math.abs(c2.getX() - c1.getX()) == 2 && Math.abs(c2.getY() - c1.getY()) == 2) {
            int dx = c1.getX() + (c2.getX() - c1.getX()) / 2;
            int dy = c1.getY() + (c2.getY() - c1.getY()) / 2;
            Cell c = board.getField()[dx][dy];
            board.getCheckerToCell().put(board.getCellToChecker().get(c), null);
            board.getCellToChecker().put(c, null);
        }
    }

}
