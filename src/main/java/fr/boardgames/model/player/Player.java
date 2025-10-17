package fr.boardgames.model.player;

import java.io.Serializable;

/**
 * Classe Joueur sérializable
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private String symbol;
    private transient PlayerStats stats; // les stats sont sauvegardées séparément

    /**
     * Constructeur du joueur
     * @param name nom défini dans UserInteraction
     * @param symbol "X" ou "O"
     */
    public Player(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    // Getters
    public String getName() { return name; }
    public String getSymbol() {
        return symbol;
    }
    public PlayerStats getStats() {return stats; }

    /**
     * Affichage du joueur
     * @return son nom et son symbole
     */
    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
