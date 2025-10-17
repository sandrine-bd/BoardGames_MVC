package fr.boardgames.view;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.ArtificialPlayer;
import fr.boardgames.model.player.HumanPlayer;
import fr.boardgames.model.player.Player;

import java.util.Random;
import java.util.Scanner;

public class UserInteraction {
    private final Scanner sc;

    public UserInteraction() {
        this.sc = new Scanner(System.in);
    }

    public int chooseGame() {
        System.out.println("\n=== CHOIX DE JEU ===");
        System.out.println("1. Tic Tac Toe (3x3)");
        System.out.println("2. Gomoku (15x15)");
        System.out.println("3. Puissance 4 (6x7)");
        System.out.print("Votre choix : ");

        int choice = 0;
        while (choice < 1 || choice > 3) {
            if (!sc.hasNextInt()){
                System.out.println("Erreur : vous devez entrer un nombre entre 1 et 3 !");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Erreur : le choix doit être 1, 2 ou 3 !");
            }
        }
        return choice;
    }

    public int setUpGameMode() {
        System.out.println("\n=== MODE DE JEU ===");
        System.out.println("1. Deux joueurs humains");
        System.out.println("2. Un humain et un joueur artificiel");
        System.out.println("3. Deux joueurs artificiels");
        System.out.print("Votre choix : ");

        int choice = 0;
        while (choice < 1 || choice > 3) {
            if (!sc.hasNextInt()){
                System.out.println("Erreur : vous devez entrer un nombre entre 1 et 3 !");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > 3) {
                System.out.println("Erreur : le choix doit être 1, 2 ou 3 !");
            }
        }
        return choice;
    }

    public Player[] setUpPlayers(int mode) {
        Player player1;
        Player player2;

        switch (mode) {
            case 1 -> {
                player1 = new HumanPlayer("Joueur 1", " \u001B[36mX\u001B[0m "); // code couleur
                player2 = new HumanPlayer("Joueur 2", " \u001B[35mO\u001B[0m ");
            }
            case 2 -> {
                player1 = new HumanPlayer("Joueur 1", " \u001B[36mX\u001B[0m ");
                player2 = new ArtificialPlayer("Joueur 2", " \u001B[35mO\u001B[0m ");
            }
            case 3 -> {
                player1 = new ArtificialPlayer("Joueur 1", " \u001B[36mX\u001B[0m ");
                player2 = new ArtificialPlayer( "Joueur 2", " \u001B[35mO\u001B[0m ");
            }
            default -> {
                System.out.println("Choix invalide, par défaut : deux joueurs humains.");
                player1 = new HumanPlayer("Joueur 1", " \u001B[36mX\u001B[0m ");
                player2 = new HumanPlayer("Joueur 2", " \u001B[35mO\u001B[0m ");
            }
        }
        return new Player[]{player1, player2};
    }

    public int[] getMoveFromPlayer(Game game, Player player) {
        if (player instanceof HumanPlayer) {
            return askCellChoice(game);
        } else {
            Random rand = new Random();
            int row, col;
            do {
                row = rand.nextInt(game.board.length); // entre 0 (inclus) et 3 (exclu)
                col = rand.nextInt(game.board.length);
            } while (!game.isCellEmpty(row, col));

            System.out.println("L'IA joue en position " + row + " (ligne), " + col + (" (colonne)"));
            return new int[]{row, col};
        }
    }

    public int[] askCellChoice(Game game) {
        int rows = game.getRows();
        int cols = game.getCols();

        while (true) {
            System.out.print("Entrez le numéro de ligne (entre 0 et 2) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : entrez un nombre !");
                sc.next();
                continue;
            }
            int row = sc.nextInt();

            System.out.print("Entrez le numéro de colonne (entre 0 et 2) : ");
            if (!sc.hasNextInt()) {
                System.out.println("Erreur : entrez un nombre !");
                sc.next();
                continue;
            }
            int col = sc.nextInt();

            if (row < 0 || col < 0 || row >= rows || col >= cols) {
                System.out.println("Erreur : coordonnées hors du plateau !");
                continue;
            }

            return new int[] {row, col};
        }
    }

    // Fermer le scanner
    public void close() {
        sc.close();
    }
}
