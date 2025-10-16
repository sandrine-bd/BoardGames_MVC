package fr.boardgames.persistence;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.PlayerStats;

import java.io.IOException;
import java.util.List;

public interface Persistence {

    // Sauvegarde et chargement des parties
    void saveGame(Game game, String filename) throws IOException;
    Game loadGame(String filename) throws IOException, ClassNotFoundException;
    List<String> listSavedGames() throws IOException;
    boolean deteleSavedGame(String filename) throws IOException;

    // Sauvegarde et chargement des statistiques joueurs
    void savePlayerStats(PlayerStats stats, String playerName) throws IOException;
    PlayerStats loadPlayerStats(String playerName) throws IOException, ClassNotFoundException;
    List<PlayerStats> loadAllPlayerStats() throws IOException, ClassNotFoundException;

    // Sauvegarde et chargement de la configuration
    void saveGameConfig(GameConfig config) throws IOException;
    GameConfig loadGameConfig() throws IOException, ClassNotFoundException;

    // Utilitaires
    boolean fileExists(String filename);
    void clearAllSaves() throws IOException;
}
