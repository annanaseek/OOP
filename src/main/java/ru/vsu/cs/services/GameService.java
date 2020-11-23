package ru.vsu.cs.services;
import ru.vsu.cs.essences.Cell;
import ru.vsu.cs.essences.Game;
import ru.vsu.cs.essences.Player;

import java.util.Scanner;

public class GameService {
    private BoardService boardService = new BoardService();
    private CheckerService checkerService = new CheckerService();

    private void playGame(Game game) {
        Player player = new Player();
        player.first = true;

        while (true) {
            drawField(game);
            System.out.println("сейчас ходит игрок " + player.toString());
            System.out.println("введите координаты клеток, откуда и куда ходить в виде: a1, b2");
            Scanner scn = new Scanner(System.in);
            int[] coordinates = getCoordinates(scn.nextLine());
            Cell c1 = game.getBoard().getField()[coordinates[0]][coordinates[1]];
            Cell c2 = game.getBoard().getField()[coordinates[2]][coordinates[3]];
            doStep(c1, c2, game, player);
        }

    }

    private void drawField(Game game) {
        Cell[][] cells = game.getBoard().getField();

        System.out.print("   ");
        for (char i = 'a'; i <= 'h'; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = cells.length - 1; i >= 0; i--) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < cells[0].length; j++) {
                if (game.getBoard().getCellToChecker().get(cells[i][j]) == null) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(game.getBoard().getCellToChecker().get(cells[i][j]).toString() + " ");
                }
            }
            System.out.print("| " + (i + 1));
            System.out.println();
        }
        System.out.print("   ");
        for (char i = 'a'; i <= 'h'; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private int[] getCoordinates(String s) {
        int[] coordinates = new int[4];
        char[] c = s.toCharArray();
        coordinates[1] = getOneCoordinate(c[0]);
        coordinates[0] = getOneCoordinate(c[1]);
        coordinates[3] = getOneCoordinate(c[4]);
        coordinates[2] = getOneCoordinate(c[5]);
        return coordinates;
    }

    private int getOneCoordinate(char c) {
        switch (c) {
            case 'a':
            case '1':
                return 0;
            case 'b':
            case '2':
                return 1;
            case 'c':
            case '3':
                return 2;
            case 'd':
            case '4':
                return 3;
            case 'e':
            case '5':
                return 4;
            case 'f':
            case '6':
                return 5;
            case 'g':
            case '7':
                return 6;
            case 'h':
            case '8':
                return 7;
        }
        return -1;
    }

    private void doStep(Cell c1, Cell c2, Game game, Player player) {
        if (checkerService.canDoStep(c1, c2, player, game.getBoard())) {
            checkerService.doStep(c1, c2, game.getBoard());
            changePlayer(player);
        }
    }

    private void changePlayer(Player player) {
        player.first = !player.isFirst();
    }

    public void startGame (Game game) {
        boardService.fillBoard(game.getBoard());
        playGame(game);
    }
}
