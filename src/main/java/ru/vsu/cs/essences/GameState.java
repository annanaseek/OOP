package ru.vsu.cs.essences;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private Board board;
    @JsonSerialize
    private List<Player> players;
    private Player activePlayer;

    public GameState() {
        board = new Board();
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player nextPlayer(){
        int index = players.indexOf(activePlayer);
        index = index == players.size() - 1 ? 0 : index + 1;
        activePlayer = players.get(index);
        return activePlayer;
    }

    public void setActivePlayer() {
        this.activePlayer = players.get(players.size() - 1);
    }

    public Board getBoard() {
        return board;
    }


    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
}
