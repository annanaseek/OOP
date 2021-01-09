package ru.vsu.cs.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vsu.cs.essences.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameService {
    private BoardService boardService = new BoardService();
    private CheckerService checkerService = new CheckerService();
    private Random random = new Random();

    private Scanner scanner = new Scanner(System.in);

    public void startGame(GameState gameState) {
        /*System.out.println("Начать новую игру - введите 1; восстановить старую игру - введите 2.");
        int command = Integer.parseInt(scanner.nextLine());
        if (command == 2) {
            if(!loadGame(gameState)) {
                System.out.println("Ошибка загрузки игры");
                return;
            }
        } else {
            boardService.fillBoard(gameState.getBoard());
            gameState.addPlayer(new Player(true, "White"));
            gameState.addPlayer(new Player(false, "Black"));
            gameState.setActivePlayer();
        }*/

        boardService.fillBoard(gameState.getBoard());
        gameState.addPlayer(new Player(true, "White"));
        gameState.addPlayer(new Player(false, "Black"));
        gameState.setActivePlayer();

        playGame(gameState);
    }

    private void playGame(GameState gameState) {

        while (true) {
            drawField(gameState);
            int command = getCommand();
            switch (command) {
                case 1:
                    if (!doGameStep(gameState.nextPlayer(), gameState.getBoard())) {
                        printLoser(gameState.getActivePlayer());
                        return;
                    }
                    break;
                case 2:
                    saveGame(gameState);
                    return;
                case 3:
                    return;
            }
        }
    }

    private boolean doGameStep(Player player, Board board) {
        List<Checker> playersCheckers = board.getCheckersByColor(player.isFirst());
        for (Checker checker : playersCheckers) {
            Map<Cell, Checker> attack = checkerService.getAttackableCells(board, checker);
            if (!attack.isEmpty()) {
                int index = random.nextInt(attack.size());
                Cell cell = (Cell) attack.keySet().toArray()[index];
                checkerService.doAttack(board, checker, cell, attack.get(cell));
                return true;
            }
        }
        for (Checker checker : playersCheckers) {
            List<Cell> steps = checkerService.getAvailableCells(board, checker);
            if (!steps.isEmpty()) {
                int index = random.nextInt(steps.size());
                checkerService.doStep(board, checker, steps.get(index));
                return true;
            }
        }
        return false;
    }

    private void drawField(GameState gameState) {
        StringBuilder sb = new StringBuilder();
        Board board = gameState.getBoard();

        sb.append(System.lineSeparator());
        sb.append("    ");
        for (char i = 'a'; i <= 'h'; i++) {
            sb.append(i + " ");
        }
        sb.append(System.lineSeparator());

        for (int i = board.FIELD_SIZE - 1; i >= 0; i--) {
            sb.append(i + 1).append(" |");
            for (int j = 0; j < board.FIELD_SIZE; j++) {
                Cell cell = board.getCell(new Coordinates(j, i));
                if (cell == null || board.getChecker(cell) == null) {
                    sb.append("⬜");
                } else {
                    Checker checker = board.getChecker(cell);
                    sb.append(checker.forPrint());
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }

    private void saveGame(GameState gameState) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("gameState.json"), gameState);
            System.out.println("Игра сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении игры");
        }
    }

    private boolean loadGame(GameState gameState) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            GameState loadGameState = mapper.readValue(new File("gameState.json"), GameState.class);

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private int getCommand() {
        System.out.println("Выберете номер команды: 1 - следующий шаг; 2 - соханить игру; 3 - выйти из игры");
        int command = Integer.parseInt(scanner.nextLine());
        if (command < 1 || command > 3) {
            return getCommand();
        }
        return command;
    }

    private void printLoser(Player player) {
        System.out.println("Проиграл " + player.getName());
    }


}
