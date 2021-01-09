package ru.vsu.cs.essences;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize
public class Player {
    private boolean first;
    private String name;

    public Player(boolean first, String name) {
        this.first = first;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return first;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return first == player.first &&
                name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, name);
    }
}
