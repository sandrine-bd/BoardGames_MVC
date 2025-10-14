package org.boardgames.model.tictactoe;

import org.boardgames.model.Game;
import org.boardgames.model.state.GameStateVisitor;
import org.boardgames.view.ConsoleView;
import org.boardgames.view.UserInteraction;

public class TicTacToeVisitor implements GameStateVisitor {
    private final ConsoleView view;
    private final UserInteraction ui;

    public TicTacToeVisitor(ConsoleView view, UserInteraction ui) {
        this.view = view;
        this.ui = ui;
    }

    @Override
    public void visit (Game game) {
        if (!(game instanceof TicTacToe ticTacToe)) return;

        switch (ticTacToe.getState()) {
            case INIT -> {
                view.displayMessage("---- DÉMARRAGE DU JEU DE TIC TAC TOE ----");
                ticTacToe.setState(TicTacToeState.PLAYER_TURN);
            }

            case PLAYER_TURN -> {
                view.displayBoard(ticTacToe.board);
                view.displayMessage("\n---- TOUR DU JOUEUR " + ticTacToe.currentPlayer.getSymbol() + "----");
                int[] move = ui.getMoveFromPlayer(ticTacToe, ticTacToe.currentPlayer);

                if (ticTacToe.isCellEmpty(move[0], move[1])) {
                    ticTacToe.setOwner(move[0], move[1], ticTacToe.currentPlayer);
                    ticTacToe.setState(TicTacToeState.CHECK_WIN);
                } else {
                    view.displayMessage("Case déjà prise !");
                }
            }

            case CHECK_WIN -> {
                if (ticTacToe.isWinner(ticTacToe.currentPlayer)) {
                    view.displayMessage("FIN DU JEU ! Le joueur " + ticTacToe.currentPlayer.getSymbol() + " a gagné !");
                    ticTacToe.setState(TicTacToeState.GAME_OVER);
                } else if (ticTacToe.isBoardFull()) {
                    view.displayMessage("MATCH NUL ! Le plateau est plein.");
                    ticTacToe.setState(TicTacToeState.GAME_OVER);
                } else {
                    ticTacToe.switchPlayer();
                    ticTacToe.setState(TicTacToeState.PLAYER_TURN);
                }
            }

            case GAME_OVER -> {
                view.displayMessage("Fin du jeu Tic Tac Toe !");
                view.displayBoard(game.board);
            }
        }
    }
}
