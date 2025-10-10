package org.tictactoe.model;

public class Cell {
    private String state;

    public Cell() { this.state = "   "; }

    public boolean isEmpty() {
        return state.equals("   ");
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSymbol() {
        return state;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
