package fr.boardgames.model.gomoku;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.game.GameStateVisitor;
import fr.boardgames.view.ConsoleView;
import fr.boardgames.view.UserInteraction;

public class GomokuVisitor implements GameStateVisitor {
    private final ConsoleView view;
    private final UserInteraction ui;

    public GomokuVisitor(ConsoleView view, UserInteraction ui) {
        this.view = view;
        this.ui = ui;
    }

    @Override
    public void visit(Game game) {
        if (!(game instanceof Gomoku gomoku)) return;

        switch (gomoku.getState()) {
            case INIT -> {
                view.displayMessage("\nDÉMARRAGE DU JEU GOMOKU");
                gomoku.setState(GomokuState.PLAYER_TURN);
            }

            case PLAYER_TURN -> {
                view.displayBoard(gomoku.board);
                view.displayMessage("\n---- TOUR DU JOUEUR " + gomoku.currentPlayer.getSymbol() + "----");
                int[] move = ui.getMoveFromPlayer(gomoku, gomoku.currentPlayer);

                if (gomoku.isCellEmpty(move[0], move[1])) {
                    gomoku.setOwner(move[0], move[1], gomoku.currentPlayer);
                    gomoku.setState(GomokuState.CHECK_WIN);
                } else {
                    view.displayMessage("Case déjà prise !");
                }
            }

            case CHECK_WIN -> {
                if (gomoku.isWinner(gomoku.currentPlayer)) {
                    view.displayMessage("\nFIN DU JEU ! Le joueur " + gomoku.currentPlayer.getSymbol() + " a gagné !");
                    gomoku.setState(GomokuState.GAME_OVER);
                } else if (gomoku.isBoardFull()) {
                    view.displayMessage("\nMATCH NUL ! Le plateau est plein.");
                    gomoku.setState(GomokuState.GAME_OVER);
                } else {
                    gomoku.switchPlayer();
                    gomoku.setState(GomokuState.PLAYER_TURN);
                }
            }

            case GAME_OVER -> {
                view.displayBoard(game.board);
            }
        }
    }
}
