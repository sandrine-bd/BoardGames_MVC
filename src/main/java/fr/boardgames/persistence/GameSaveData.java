package fr.boardgames.persistence;

import fr.boardgames.model.game.Game;

import java.io.Serializable;

public class GameSaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Game game;
    private final long timestamp;
    private final String gameType;
    private final String description;

    public GameSaveData(Game game, long timestamp, String gameType) {
        this.game = game;
        this.timestamp = timestamp;
        this.gameType = gameType;
        this.description = generateDescription(game);
    }

    private String generateDescription(Game game) {
        return "Plateau " + game.getRows() + "x" + game.getCols() + " - Tour de " + game.currentPlayer.getSymbol();
    }

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
