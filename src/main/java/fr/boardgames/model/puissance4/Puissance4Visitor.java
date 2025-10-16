package fr.boardgames.model.puissance4;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.game.GameStateVisitor;
import fr.boardgames.view.ConsoleView;
import fr.boardgames.view.UserInteraction;

public class Puissance4Visitor implements GameStateVisitor {
    private final ConsoleView view;
    private final UserInteraction ui;

    public Puissance4Visitor(ConsoleView view, UserInteraction ui) {
        this.view = view;
        this.ui = ui;
    }

    @Override
    public void visit(Game game) {
        if (!(game instanceof Puissance4 puissance4)) return;

        switch (puissance4.getState()) {
            case INIT -> {
                view.displayMessage("\nDÉMARRAGE DU JEU PUISSANCE 4");
                view.displayMessage("Plateau 6x7 - Alignez 4 pièces pour gagner !");
                puissance4.setState(fr.boardgames.model.puissance4.Puissance4State.PLAYER_TURN);
            }

            case PLAYER_TURN -> {
                view.displayBoard(puissance4.board);
                view.displayMessage("\n=== TOUR DU JOUEUR " + puissance4.currentPlayer.getSymbol() + "===");

                int[] move = ui.getMoveFromPlayer(puissance4, puissance4.currentPlayer);

                if (puissance4.isCellEmpty(move[0], move[1])) {
                    puissance4.setOwner(move[0], move[1], puissance4.currentPlayer);
                    puissance4.setState(fr.boardgames.model.puissance4.Puissance4State.CHECK_WIN);
                } else {
                    view.displayMessage("Case déjà prise !");
                }
            }

            case CHECK_WIN -> {
                if (puissance4.isWinner(puissance4.currentPlayer)) {
                    view.displayMessage("\nFIN DU JEU ! Le joueur " + puissance4.currentPlayer.getSymbol() + " a gagné !");
                    puissance4.setState(Puissance4State.GAME_OVER);
                } else if (puissance4.isBoardFull()) {
                    view.displayMessage("\nMATCH NUL ! Le plateau est plein.");
                    puissance4.setState(Puissance4State.GAME_OVER);
                } else {
                    puissance4.switchPlayer();
                    puissance4.setState(Puissance4State.PLAYER_TURN);
                }
            }

            case GAME_OVER -> {
                view.displayBoard(game.board);
                view.displayMessage("\nFIN DU PUISSANCE 4");
            }
        }
    }
}
