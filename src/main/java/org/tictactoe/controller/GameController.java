package org.tictactoe.controller;

import org.tictactoe.model.Game;
import org.tictactoe.view.ConsoleView;
import org.tictactoe.view.UserInteraction;

public class GameController {
    private final Game game;
    private final ConsoleView view;
    private final UserInteraction ui;

    public GameController(Game game, ConsoleView view, UserInteraction ui) {
        this.game = game;
        this.view = view;
        this.ui = ui;
    }

    public void play() {
        while (true) {
            view.displayBoard(game.board);
            view.displayMessage("\n---- TOUR DU JOUEUR " + game.currentPlayer.getSymbol() + "----");

            int[] move = ui.askCellChoice();

            if (game.isCellEmpty(move[0], move[1])) {
                game.setOwner(move[0], move[1], game.currentPlayer);
            } else {
                view.displayMessage("Case déjà prise !");
                continue;
            }

            if (game.isWinner(game.currentPlayer)) {
                view.displayMessage("FIN DU JEU ! Le joueur " + game.currentPlayer.getSymbol() + " a gagné !");
                break;
            }

            if (game.isBoardFull()) {
               view.displayMessage("Match nul ! Le plateau est plein.");
                break;
            }

            game.switchPlayer();
        }
    }
}
