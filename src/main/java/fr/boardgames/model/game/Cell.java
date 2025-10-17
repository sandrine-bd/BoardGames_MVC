package fr.boardgames.model.game;

import java.io.Serializable;

public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    public Cell() { this.symbol = "   "; }

    public boolean isEmpty() {
        return symbol.equals("   ");
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void clear() {
        this.symbol = "   ";
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
