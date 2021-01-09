package ru.vsu.cs;

import ru.vsu.cs.essences.GameState;
import ru.vsu.cs.services.GameService;

public class Main {

    public static void main(String[] args) {
        GameState gameState = new GameState();
        GameService gs = new GameService();
        gs.startGame(gameState);
    }
}
