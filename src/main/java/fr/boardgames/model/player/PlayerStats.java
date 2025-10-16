package fr.boardgames.model.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlayerStats implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String playerName;
    private final Map<String, GameStats> statsByGame;
    private long totalPlayTime; // en millisecondes
    private long lastPlayedTimestamp;

    public PlayerStats(String playerName) {
        this.playerName = playerName;
        this.statsByGame = new HashMap<>();
        this.totalPlayTime = 0;
        this.lastPlayedTimestamp = System.currentTimeMillis();
    }

    public void recordWin(String gameType) {
        getOrCreateStats(gameType).recordWin();
        updateLastPlayed();
    }

    public void recordLoss(String gameType) {
        getOrCreateStats(gameType).recordLoss();
        updateLastPlayed();
    }

    public void recordDraw(String gameType) {
        getOrCreateStats(gameType).recordDraw();
        updateLastPlayed();
    }

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
    public int getTotalDraws() { return statsByGame.values().stream().mapToInt(GameStats::getDraws).sum(); }
    public double getWinRate() {
        int total = getTotalWins() + getTotalLosses() + getTotalDraws();
        return total == 0 ? 0.0 : (double) getTotalWins() / total * 100;
    }

    @Override
    public String toString() {
        return String.format("Joueur : %s | Victoires : %d | Défaites : %d | N : %d | Taux de réussite : %.1f%%",
        playerName, getTotalWins(), getTotalLosses(), getTotalDraws(), getWinRate());
    }

    // Classe interne pour les stats par jeu
    public static class GameStats implements Serializable {
        private static final long serialVersionUID = 1L;

        private int wins;
        private int losses;
        private int draws;

        public GameStats() {
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
        }

        public void recordWin() { wins++; }
        public void recordLoss() { losses++; }
        public void recordDraw() { draws++; }

        public int getWins() { return wins; }
        public int getLosses() { return losses; }
        public int getDraws() { return draws; }
        public int getTotalGames() { return wins + losses + draws; }

        public double getWinRate() {
            int total = getTotalGames();
            return total == 0 ? 0.0 : (double) wins / total * 100;
        }
    }
}
