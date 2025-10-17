package fr.boardgames.model.strategy;

import fr.boardgames.model.game.Cell;
import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;

/**
 * Classe qui définit comment chaque jeu peut être gagné
 */
public class AlignmentStrategy implements WinStrategy {
    protected final int alignmentNeeded;

    /**
     * Définit la stratégie gagnante
     * @param alignmentNeeded nombre de cases à aligner
     */
    public AlignmentStrategy(int alignmentNeeded) {
        this.alignmentNeeded = alignmentNeeded;
    }

    /**
     * Méthode qui vérifie à chaque tour si un joueur a gagné
     * @param game jeu choisi par le joueur
     * @param player joueur en cours
     * @return true si la partie est gagnée
     */
    @Override
    public boolean checkWin(Game game, Player player) {
        String symbol = player.getSymbol();
        Cell[][] board = game.board;
        int rows = board.length;
        int cols = board[0].length;

        // Vérifications horizontale, verticale, diagonales descendante et montante
        if (checkHorizontal(board, symbol, rows, cols)) return true;
        if (checkVertical(board, symbol, rows, cols)) return true;
        if (checkDiagonalDescending(board, symbol, rows, cols)) return true;
        if (checkDiagonalAscending(board, symbol, rows, cols)) return true;
        return false;
    }

    private boolean checkHorizontal(Cell[][] board, String symbol, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            int count = 0;
            for (int col = 0; col < cols; col++) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(Cell[][] board, String symbol, int rows, int cols) {
        for (int col = 0; col < cols; col++) {
            int count = 0;
            for (int row = 0; row < rows; row++) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalDescending(Cell[][] board, String symbol, int rows, int cols) {
        // part de la première ligne
        for (int startCol = 0; startCol < rows; startCol++) {
            int count = 0;
            for (int row = 0, col = startCol; row < rows && col < cols; row++, col++) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }

        // part de la première colonne (sauf coin déjà traité)
        for (int startRow = 1; startRow < rows; startRow++) {
            int count = 0;
            for (int row = startRow, col = 0; row < rows && col < cols; row++, col++) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalAscending(Cell[][] board, String symbol, int rows, int cols) {
        // part de la première ligne
        for (int startCol = 0; startCol < rows; startCol++) {
            int count = 0;
            for (int row = 0, col = startCol; row < rows && col >= 0; row++, col--) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }

        // part de la première colonne
        for (int startRow = 1; startRow < rows; startRow++) {
            int count = 0;
            for (int row = startRow, col = cols - 1; row < rows && col >= 0; row++, col--) {
                if (board[row][col].getSymbol().equals(symbol)) {
                    count++;
                    if (isWinningAlignment(count)) return true;
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /**
     * Méthode qui compare le nombre de cases alignées par un joueur au nombre de cases définies pour gagner
     * Méthode à surcharger pour Gomoku (exactement 5)
     * @param count nombre de cases à aligner pour gagner
     * @return true si l'alignement est supérieur ou égal à ce nombre
     */
    protected boolean isWinningAlignment(int count) {
        return count >= alignmentNeeded;
    }
}
