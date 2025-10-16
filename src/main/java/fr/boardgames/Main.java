package fr.boardgames;

import fr.boardgames.controller.GameController;
import fr.boardgames.model.game.Game;
import fr.boardgames.model.game.GameStateVisitor;
import fr.boardgames.model.player.Player;
import fr.boardgames.view.ConsoleView;
import fr.boardgames.view.UserInteraction;
import fr.boardgames.model.tictactoe.TicTacToe;
import fr.boardgames.model.tictactoe.TicTacToeVisitor;
import fr.boardgames.model.gomoku.Gomoku;
import fr.boardgames.model.gomoku.GomokuVisitor;
import fr.boardgames.model.puissance4.Puissance4;
import fr.boardgames.model.puissance4.Puissance4Visitor;

public class Main {
    public static void main(String[] args) {
        UserInteraction ui = new UserInteraction();
        ConsoleView view = new ConsoleView();

        int gameChoice = ui.chooseGame();
        int mode = ui.setUpGameMode();
        Player[] players = ui.setUpPlayers(mode);

        Game game;
        GameStateVisitor visitor;

        switch (gameChoice) {
            case 1 -> {
                game = new TicTacToe(new int[]{3,3}, players[0], players[1]);
                visitor = new TicTacToeVisitor(view, ui);
            }
            case 2 -> {
                game = new Gomoku(new int[]{15,15}, players[0], players[1]);
                visitor = new GomokuVisitor(view, ui);
            }
            case 3 -> {
                game = new Puissance4(new int[]{6,7}, players[0], players[1]);
                visitor = new Puissance4Visitor(view, ui);
            }
            default -> throw new IllegalArgumentException("Jeu inconnu");
        }

        GameController controller = new GameController(game, visitor);
        controller.play();
    }
}