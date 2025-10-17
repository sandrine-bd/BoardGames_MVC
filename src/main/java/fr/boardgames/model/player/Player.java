package fr.boardgames.model.player;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private String symbol;
    private transient PlayerStats stats; // les stats sont sauvegardées séparément

    public Player(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
