package ru.vsu.cs.essences;

public class Player {
    public boolean first;

    public Player() {
    }

    public boolean isFirst() {
        return first;
    }

    public String toString() {
        String stringPlayer;
        if (isFirst()) {
            stringPlayer = "1";
        } else {
            stringPlayer = "2";
        }
        return stringPlayer;

    }
}
