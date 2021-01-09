package ru.vsu.cs.essences;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class Checker {

    private boolean isWhite;
    private boolean isQueen;

    public Checker(boolean isWhite) {
        this.isWhite = isWhite;
        this.isQueen = false;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isQueen() {
        return isQueen;
    }

    public void setQueen(boolean queen) {
        isQueen = queen;
    }

    public String forPrint() {
        if (isWhite) {
            if (isQueen) return "⛁";
            return "⛀";
        } else if (isQueen) return "⛃";
        else return "⛂";
    }

}
