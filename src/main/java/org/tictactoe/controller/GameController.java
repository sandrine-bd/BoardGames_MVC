package org.tictactoe.controller;

import org.tictactoe.model.Game;
import org.tictactoe.model.HumanPlayer;
import org.tictactoe.model.Player;
import org.tictactoe.view.ConsoleView;
import org.tictactoe.view.UserInteraction;

import java.util.Random;

public class GameController {
    private final Game game;
    private final ConsoleView view;
    private final UserInteraction ui;

    public GameController(Game game, ConsoleView view, UserInteraction ui) {
        this.game = game;
        this.view = view;
        this.ui = ui;
    }

    public int[] getMoveFromPlayer (Player player) {
        if (player instanceof HumanPlayer) {
            return ui.askCellChoice();
        } else {
            Random rand = new Random();
            int row, col;
            do {
                row = rand.nextInt(game.board.length); // entre 0 (inclus) et 3 (exclu)
                col = rand.nextInt(game.board.length);
            } while (!game.isCellEmpty(row, col));

            view.displayMessage("L'IA joue en position " + row + " (ligne), " + col + (" (colonne)"));
            return new int[]{row, col};
        }
    }

    public void play() {
        while (true) {
            view.displayBoard(game.board);
            view.displayMessage("\n---- TOUR DU JOUEUR " + game.currentPlayer.getSymbol() + "----");

            int[] move = getMoveFromPlayer(game.currentPlayer);

            if (game.isCellEmpty(move[0], move[1])) {
                game.setOwner(move[0], move[1], game.currentPlayer);
            } else {
                view.displayMessage("Case déjà prise !");
                continue;
            }

            if (game.isWinner(game.currentPlayer)) {
                view.displayMessage("FIN DU JEU ! Le joueur " + game.currentPlayer.getSymbol() + " a gagné !");
                view.displayBoard(game.board);
                break;
            }

            if (game.isBoardFull()) {
               view.displayMessage("Match nul ! Le plateau est plein.");
               view.displayBoard(game.board);
                break;
            }

            game.switchPlayer();
        }
    }
}
