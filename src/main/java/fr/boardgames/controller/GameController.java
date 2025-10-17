package fr.boardgames.controller;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.game.GameStateVisitor;
import fr.boardgames.model.player.PlayerStats;
import fr.boardgames.persistence.GameSerialization;
import fr.boardgames.persistence.Persistence;

import java.io.IOException;

/**
 * Controleur général du programme
 */
public class GameController {
    private final Game game;
    private GameStateVisitor visitor;
    private final Persistence persistence;

    /**
     * Constructeur
     * @param game jeu choisi
     * @param visitor visiteur du jeu choisi
     */
    public GameController(Game game, GameStateVisitor visitor) {
        this.game = game;
        this.visitor = visitor;
        this.persistence = new GameSerialization();
    }

    /**
     * Méthode principale qui lance le jeu et à la fin le sauvegarde
     */
    public void play() {
        // visite initiale
        game.accept(visitor);

        // boucle principale
        while (!game.isGameOver()) {
            game.accept(visitor);
        }

        // visite finale pour afficher l'état GAME_OVER
        game.accept(visitor);

        // sauvegarde les statistiques à la fin
        saveGameStats();
    }

    /**
     * Sauvegarde du jeu dans un fichier
     * @param filename nom du fichier
     */
    public void saveGame(String filename) {
        try {
            persistence.saveGame(game, filename);
        } catch (IOException e) {
            System.err.println("Impossible de sauvegarder la partie : " + e.getMessage());
        }
    }

    private void saveGameStats() {
        try {
            String gameType = game.getClass().getSimpleName();

            // Charger ou créer les stats des joueurs
            PlayerStats stats1 = loadOrCreateStats(game.getPlayer1().getName());
            PlayerStats stats2 = loadOrCreateStats(game.getPlayer2().getName());

            // Enregistrer le résultat
            if (game.isWinner(game.getPlayer1())) {
                stats1.recordWin(gameType);
                stats2.recordLoss(gameType);
            } else if (game.isWinner(game.getPlayer2())) {
                stats1.recordLoss(gameType);
                stats2.recordWin(gameType);
            }

            // Ajouter le temps de jeu
            long playTime = game.getElapsedTime();
            stats1.addPlayTime(playTime);
            stats2.addPlayTime(playTime);

            // Sauvegarder
            persistence.savePlayerStats(stats1, game.getPlayer1().getName());
            persistence.savePlayerStats(stats2, game.getPlayer2().getName());

        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde des stats : " + e.getMessage());
        }
    }

    private PlayerStats loadOrCreateStats(String playerName) {
        try {
            return persistence.loadPlayerStats(playerName);
        } catch (Exception e) {
            return new PlayerStats(playerName);
        }
    }
}