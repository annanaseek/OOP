package ru.vsu.cs;

import ru.vsu.cs.essences.Game;
import ru.vsu.cs.services.GameService;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        GameService gs = new GameService();
        gs.startGame(game);
    }
}
