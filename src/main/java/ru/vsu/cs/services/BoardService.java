package ru.vsu.cs.services;

import ru.vsu.cs.essences.Board;
import ru.vsu.cs.essences.Checker;


public class BoardService {
    public void fillBoard(Board board) {

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

}
