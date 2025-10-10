package org.tictactoe;

import java.util.Random;

public class Game {
    protected Cell[][] board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public void initializeBoard() {
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void setOwner(int row, int col, Player player) {
        board[row][col].setState(player.getRepresentation());
    }

    public boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isEmpty()) {
                    return false; // s'il y a une case vide = plateau non plein
                }
            }
        } return true; // aucune case vidé trouvée = plateau plein
    }

    public boolean isWinner(Player player) {
        return false;
    }

    public int[] getMoveFromPlayer(Player player) {
        if (player instanceof HumanPlayer) {
            return ui.askCellChoice(size, board);
        }
        // sinon : joueur artificiel
        Random rand = new Random();
        while (true) {
            int row = rand.nextInt(size[0]); // entre 0 (inclus) et size (exclu)
            int col = rand.nextInt(size[1]);
            if (board[row][col].isEmpty()) {
                System.out.println("L'IA joue en position " + row + " (ligne), " + col + (" (colonne)"));
                return new int[]{row, col};
            }
        }
    }
}
