package ru.vsu.cs.essences;

public class Checker {

    private boolean isWhite;
    Player player;

    public Checker(boolean isWhite, Player player) {
        this.isWhite = isWhite;
        this.player = player;
    }



    public Checker(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public boolean isWhite() {
        return isWhite;
    }

    public String toString() {
        String stringChecker = null;
        if (isWhite) {
            stringChecker = "1";
        }
        if (!isWhite) {
            stringChecker = "2";
        }
        return stringChecker;
    }
}
