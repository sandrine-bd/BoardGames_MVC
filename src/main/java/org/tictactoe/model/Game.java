package org.tictactoe.model;

public abstract class Game {
    public Cell[][] board;
    public Player player1;
    public Player player2;
    public Player currentPlayer;

    public Game(int size, Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Cell[size][size];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setState(player.getSymbol());
    }

    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
            throw new IllegalArgumentException("Coordonnées hors du plateau !");
        }
        return board[row][col].isEmpty(); // renvoie true si la case ne contient pas de symbole
    }

    public boolean isWinner(Player player) {
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        } return true;
    }

    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
