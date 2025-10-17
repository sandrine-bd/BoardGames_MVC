package fr.boardgames.persistence;

import fr.boardgames.model.game.Game;

import java.io.Serializable;

/**
 * Classe sérializable pour sauvegarder chaque jeu joué, sa date, son type, sa description
 */
public class GameSaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Game game;
    private final long timestamp;
    private final String gameType;
    private final String description;

    /**
     * Constructeur avec un jeu, sa date, son type, et génère sa description
     * @param game jeu
     * @param timestamp date de jeu
     * @param gameType type de jeu
     */
    public GameSaveData(Game game, long timestamp, String gameType) {
        this.game = game;
        this.timestamp = timestamp;
        this.gameType = gameType;
        this.description = generateDescription(game);
    }

    /**
     * Affiche la taille de la grille du jeu et le symbole du joueur en train de jouer
     * @param game jeu choisi
     * @return String
     */
    private String generateDescription(Game game) {
        return "Plateau " + game.getRows() + "x" + game.getCols() + " - Tour de " + game.currentPlayer.getSymbol();
    }

    // Getters
    public Game getGame() {
        return game;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public String getGameType() {
        return gameType;
    }
    public String getDescription() {
        return description;
    }
}
