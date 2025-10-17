package fr.boardgames.persistence;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.PlayerStats;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe de sérialization avec toutes les méthodes de sauvegarde et chargement des données des jeux
 */

public class GameSerialization implements Persistence {

    private static final String SAVES_DIRECTORY = "saves/";
    private static final String GAMES_DIRECTORY = SAVES_DIRECTORY + "games/";
    private static final String STATS_DIRECTORY = SAVES_DIRECTORY + "stats/";
    private static final String CONFIG_FILE = SAVES_DIRECTORY + "config.dat";
    private static final String GAME_EXTENSION = ".game";
    private static final String STATS_EXTENSION = ".stats";

    /**
     * Constructeur qui crée les dossiers où seront enregistrées les données
     */
    public GameSerialization() {
        initializeDirectories();
    }

    private void initializeDirectories() {
        try {
            Files.createDirectories(Paths.get(GAMES_DIRECTORY));
            Files.createDirectories(Paths.get(STATS_DIRECTORY));
        } catch (IOException e) {
            System.err.println("Erreur lors de la création des répertoires : " + e.getMessage());
        }
    }

    // ====== SAUVEGARDE ET CHARGEMENT DES PARTIES ======

    @Override
    public void saveGame(Game game, String filename) throws IOException {
        if (!filename.endsWith(GAME_EXTENSION)) {
            filename += GAME_EXTENSION;
        }

        String filepath = GAMES_DIRECTORY + filename;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            GameSaveData saveData = new GameSaveData(game, System.currentTimeMillis(), game.getClass().getSimpleName());
            oos.writeObject(saveData);
            System.out.println("Partie sauvegardée : " + filepath);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde :" + e.getMessage());
            throw e;
        }
    }

    @Override
    public Game loadGame(String filename) throws IOException, ClassNotFoundException {
        if (!filename.endsWith(GAME_EXTENSION)) {
            filename += GAME_EXTENSION;
        }

        String filepath = GAMES_DIRECTORY + filename;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            GameSaveData saveData = (GameSaveData) ois.readObject();
            System.out.println("Partie chargée : " + saveData.getGameType() + " (sauvegardée le "
                    + new java.util.Date(saveData.getTimestamp()) + ")");
            return saveData.getGame();
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<String> listSavedGames() throws IOException {
        List<String> savedGames = new ArrayList<>();
        Path gamesPath = Paths.get(GAMES_DIRECTORY);

        if (!Files.exists(gamesPath)) {
            return savedGames;
        }

        try (Stream<Path> paths = Files.walk(gamesPath, 1)) {
            savedGames = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(GAME_EXTENSION))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        }
        return savedGames;
    }

    @Override
    public boolean deleteSavedGame(String filename) throws IOException {
        if (!filename.endsWith(GAME_EXTENSION)) {
            filename += GAME_EXTENSION;
        }

        Path filepath = Paths.get(GAMES_DIRECTORY + filename);

        if (Files.exists(filepath)) {
            Files.delete(filepath);
            System.out.println("Sauvegarde supprimée : " + filename);
            return true;
        }
        return false;
    }

    // ====== STATISTIQUES JOUEURS ======

    @Override
    public void savePlayerStats(PlayerStats stats, String playerName) throws IOException {
        String filename = sanitizeFilename(playerName) + STATS_EXTENSION;
        String filepath = STATS_DIRECTORY + filename;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(stats);
            System.out.println("Statistiques sauvegardées pour : " + playerName);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des stats : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public PlayerStats loadPlayerStats(String playerName) throws IOException, ClassNotFoundException {
        String filename = sanitizeFilename(playerName) + STATS_EXTENSION;
        String filepath = STATS_DIRECTORY + filename;

        if (Files.exists(Paths.get(filepath))) {
            return new PlayerStats(playerName); // crée des stats vides si elles n'existent pas
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
            PlayerStats stats = (PlayerStats) ois.readObject();
            System.out.println("Statistiques chargées pour : " + playerName);
            return stats;
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des stats : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<PlayerStats> loadAllPlayerStats() throws IOException, ClassNotFoundException {
        List<PlayerStats> allStats = new ArrayList<>();
        Path statsPath = Paths.get(STATS_DIRECTORY);

        if (!Files.exists(statsPath)) {
            return allStats;
        }

        try (Stream<Path> paths = Files.walk(statsPath, 1)) {
            List<Path> statFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(STATS_EXTENSION))
                    .collect(Collectors.toList());
            for (Path statFile : statFiles) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(statFile.toFile()))) {
                    PlayerStats stats = (PlayerStats) ois.readObject();
                    allStats.add(stats);
                }
            }
        }
        return allStats;
    }

    // ====== CONFIGURATION ======

    @Override
    public void saveGameConfig(GameConfig config) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE))) {
            oos.writeObject(config);
            System.out.println("Configuration sauvegardée");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de la config : " + e.getMessage());
            throw e;
        }
    }

    @Override
    public GameConfig loadGameConfig() throws IOException, ClassNotFoundException {
        if (!Files.exists(Paths.get(CONFIG_FILE))) {
            return new GameConfig(); // crée une config par défaut
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CONFIG_FILE))) {
            GameConfig config = (GameConfig) ois.readObject();
            System.out.println("Configuration chargée");
            return config;
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la config : " + e.getMessage());
            throw e;
        }
    }

    // ====== UTILITAIRES ======

    /**
     * Vérifie si un fichier existe
     * @param filename nom du fichier
     * @return le chemin vers le fichier
     */
    @Override
    public boolean fileExists(String filename) {
        return Files.exists(Paths.get(GAMES_DIRECTORY + filename));
    }

    /**
     * Supprime toutes les sauvegardes de jeux
     */
    @Override
    public void clearAllSaves() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(GAMES_DIRECTORY), 1)) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("Erreur lors de la suppression : " + path);
                        }
                    });
        }
        System.out.println("Toutes les sauvegardes ont été supprimées");
    }

    /**
     * Remplace les caractères invalides par des underscores
     * @param filename
     * @return
     */
    private String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }
}
