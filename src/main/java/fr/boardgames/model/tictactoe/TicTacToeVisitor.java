package fr.boardgames.model.tictactoe;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.game.GameStateVisitor;
import fr.boardgames.view.ConsoleView;
import fr.boardgames.view.UserInteraction;

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
                view.displayMessage("\nDÉMARRAGE DU JEU DE TIC TAC TOE");
                view.displayMessage("Plateau 3x3 - Alignez 3 symboles pour gagner !");
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
                    view.displayMessage("\nFIN DU JEU ! Le joueur " + ticTacToe.currentPlayer.getSymbol() + " a gagné !");
                    ticTacToe.setState(TicTacToeState.GAME_OVER);
                } else if (ticTacToe.isBoardFull()) {
                    view.displayMessage("\nMATCH NUL ! Le plateau est plein.");
                    ticTacToe.setState(TicTacToeState.GAME_OVER);
                } else {
                    ticTacToe.switchPlayer();
                    ticTacToe.setState(TicTacToeState.PLAYER_TURN);
                }
            }

            case GAME_OVER -> {
                view.displayBoard(game.board);
                view.displayMessage("\n---- FIN DU TIC TAC TOE ----");
            }
        }
    }
}
