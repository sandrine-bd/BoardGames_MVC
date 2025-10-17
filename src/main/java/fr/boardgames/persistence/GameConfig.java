package fr.boardgames.persistence;

import java.io.Serializable;

/**
 * Classe sérializable pour sauvegarder le nom du dernier joueur et le jeu préféré
 */
public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lastPlayerName;
    private String preferredGameType;

    /**
     * Constructeur avec nom de joueur vide et jeu préféré "Tic Tac Toe" par défaut
     */
    public GameConfig() {
        this.lastPlayerName = "";
        this.preferredGameType = "TicTacToe";
    }

    // Getters
    public String getLastPlayerName() {
        return lastPlayerName;
    }
    public String getPreferredGameType() {
        return preferredGameType;
    }

    // Setters
    public void setLastPlayerName(String lasPlayerName) {
        this.lastPlayerName = lasPlayerName;
    }
    public void setPreferredGameType(String preferredGameType) {
        this.preferredGameType = preferredGameType;
    }
}
