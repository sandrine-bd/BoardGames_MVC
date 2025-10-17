package fr.boardgames.model.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe sérializable pour enregistrer les stats de chaque joueur
 */
public class PlayerStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String playerName;
    private final Map<String, GameStats> statsByGame;
    private long totalPlayTime; // en millisecondes
    private long lastPlayedTimestamp;

    /**
     * Constructeur avec le nom du joueur, ses stats par jeu, son temps total de jeu, et la dernière fois qu'il a joué
     * @param playerName nom défini dans UserInteraction
     */
    public PlayerStats(String playerName) {
        this.playerName = playerName;
        this.statsByGame = new HashMap<>();
        this.totalPlayTime = 0;
        this.lastPlayedTimestamp = System.currentTimeMillis();
    }

    /**
     * Enregistre le nombre de fois où le joueur a gagné
     * @param gameType
     */
    public void recordWin(String gameType) {
        getOrCreateStats(gameType).recordWin();
        updateLastPlayed();
    }

    /**
     * Enregistre le nombre de fois où le joueur a perdu
     * @param gameType
     */
    public void recordLoss(String gameType) {
        getOrCreateStats(gameType).recordLoss();
        updateLastPlayed();
    }

    /**
     * Compte le temps total de jeu en millisecondes
     * @param milliseconds
     */
    public void addPlayTime(long milliseconds) {
        this.totalPlayTime += milliseconds;
    }

    private GameStats getOrCreateStats(String gameType) {
        return statsByGame.computeIfAbsent(gameType, k -> new GameStats());
    }

    private void updateLastPlayed() {
        this.lastPlayedTimestamp = System.currentTimeMillis();
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public Map<String, GameStats> getStatsByGame() { return new HashMap<>(statsByGame); }
    public long getTotalPlayTime() { return totalPlayTime; }
    public long getLastPlayedTimestamp() { return lastPlayedTimestamp; }
    public int getTotalWins() { return statsByGame.values().stream().mapToInt(GameStats::getWins).sum(); }
    public int getTotalLosses() { return statsByGame.values().stream().mapToInt(GameStats::getLosses).sum(); }
    public double getWinRate() {
        int total = getTotalWins() + getTotalLosses();
        return total == 0 ? 0.0 : (double) getTotalWins() / total * 100;
    }

    /**
     * Affiche le nom du joueur, son nombre de victoires et défaites, son taux de réussite
     * @return
     */
    @Override
    public String toString() {
        return String.format("Joueur : %s | Victoires : %d | Défaites : %d | Taux de réussite : %.1f%%",
        playerName, getTotalWins(), getTotalLosses(), getWinRate());
    }

    /**
     * Classe interne pour les stats par jeu
     */
    public static class GameStats implements Serializable {
        private static final long serialVersionUID = 1L;

        private int wins;
        private int losses;

        public GameStats() {
            this.wins = 0;
            this.losses = 0;
        }

        public void recordWin() { wins++; }
        public void recordLoss() { losses++; }

        public int getWins() { return wins; }
        public int getLosses() { return losses; }
        public int getTotalGames() { return wins + losses; }

        public double getWinRate() {
            int total = getTotalGames();
            return total == 0 ? 0.0 : (double) wins / total * 100;
        }
    }
}
