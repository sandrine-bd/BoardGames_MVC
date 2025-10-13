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
    private GameState state;

    public enum GameState {
        INIT,
        PLAYER_TURN,
        CHECK_WIN,
        GAME_OVER
    }

    public GameController(Game game, ConsoleView view, UserInteraction ui) {
        this.game = game;
        this.view = view;
        this.ui = ui;
        this.state = GameState.INIT;
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
        boolean running = true;

        while (running) {
            switch (state) {

                case INIT -> {
                    state = GameState.PLAYER_TURN;
                }

                case PLAYER_TURN -> {
                    view.displayBoard(game.board);
                    view.displayMessage("\n---- TOUR DU JOUEUR " + game.currentPlayer.getSymbol() + "----");

                    int[] move = getMoveFromPlayer(game.currentPlayer);

                    if (game.isCellEmpty(move[0], move[1])) {
                        game.setOwner(move[0], move[1], game.currentPlayer);
                        state = GameState.CHECK_WIN;
                    } else {
                        view.displayMessage("Case déjà prise !");
                    }
                }

                case CHECK_WIN -> {
                    if (game.isWinner(game.currentPlayer)) {
                        view.displayMessage("FIN DU JEU ! Le joueur " + game.currentPlayer.getSymbol() + " a gagné !");
                        state = GameState.GAME_OVER;
                    } else if (game.isBoardFull()) {
                        view.displayMessage("MATCH NUL ! Le plateau est plein.");
                        state = GameState.GAME_OVER;
                    } else {
                        game.switchPlayer();
                        state = GameState.PLAYER_TURN;
                    }
                }

                case GAME_OVER -> {
                    view.displayBoard(game.board);
                    running = false; // on quitte la boucle
                }
            }
        }
    }
}
