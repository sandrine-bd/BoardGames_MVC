package fr.boardgames.model.game;

import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.WinStrategy;

import java.io.Serializable;

/**
 * Classe abstraite sérializable qui définit la construction d'un jeu
 */
public abstract class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int[] size;
    public Cell[][] board;
    private Player player1;
    private Player player2;
    public Player currentPlayer;
    protected transient WinStrategy winStrategy; // transient car la stratégie sera recréée au chargement
    // métadonnées de la partie
    private long startTime;
    private int moveCount;

    /**
     * Constructeur utilisant une grille, deux joueurs et la règle pour gagner
     * @param size taille de la grille du jeu
     * @param player1 joueur 1
     * @param player2 joueur 2
     * @param winStrategy stratégie du jeu choisi
     */
    public Game(int[] size, Player player1, Player player2, WinStrategy winStrategy) {
        this.size = size;
        this.board = new Cell[size[0]][size[1]];
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.winStrategy = winStrategy;
        this.startTime = System.currentTimeMillis();
        this.moveCount = 0;
        initializeBoard();
    }

    /**
     * Méthode pour dessiner la grille de jeu
     */
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Méthode pour savoir si le joueur est gagnant
     * @param player
     * @return boolean
     */
    public boolean isWinner(Player player) {
        return winStrategy.checkWin(this, player);
    }

    /**
     * Méthode pour savoir si le jeu est terminé
     * @return boolean
     */
    public abstract boolean isGameOver();

    /**
     * Pattern Visitor
     * @param visitor
     */
    public void accept(GameStateVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Méthode appelée après désérialisation pour réinitialiser le jeu
     */
    protected abstract void reinitializeStrategy();

    // ========== METHODES UTILITAIRES ==========

    /**
     * Vérifie si la case est vide
     * @param row = numéro de ligne
     * @param col = numéro de colonne
     * @return true si la case ne contient pas de symbole
     */
    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
            throw new IllegalArgumentException("Coordonnées hors du plateau !");
        }
        return board[row][col].isEmpty();
    }

    /**
     * Dessine le symbole du joueur qui vient de jouer
     * @param row numéro de ligne choisie par le joueur
     * @param col numéro de colonne choisie par le joueur
     * @param player le joueur en cours
     */
    public void setOwner(int row, int col, Player player) {
        board[row][col].setSymbol(player.getSymbol());
    }

    /**
     * Vérifie si la grille de jeu est remplie à 100%
     * @return vrai si la grille est pleine
     */
    public boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Change de joueur à la fin d'un tour
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    // Getters
    public Player getPlayer1() { return player1; }
    public Player getPlayer2() { return player2; }
    public int getRows() { return board.length; }
    public int getCols() { return board[0].length; }
    public long getStartTime() { return startTime; }
    public int getMoveCount() { return moveCount; }
    public long getElapsedTime() { return System.currentTimeMillis() - startTime; }
}