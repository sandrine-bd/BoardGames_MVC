package org.tictactoe.view;

import org.tictactoe.model.Cell;

public class ConsoleView {
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

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
