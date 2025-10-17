package fr.boardgames.view;

import fr.boardgames.model.game.Cell;

/**
 * Classe dédiée à l'affichage dans la console
 */
public class ConsoleView {
    /**
     * Affichage de la grille de jeu
     */
    public void displayBoard(Cell[][] board) {
        System.out.println("-".repeat(board.length*6));
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-".repeat(board.length*6));
        }
    }

    /**
     * Affichage d'un message
     * @param message que l'on veut afficher
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
