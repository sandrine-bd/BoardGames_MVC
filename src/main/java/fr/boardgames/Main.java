package fr.boardgames;

import fr.boardgames.controller.GameController;
import fr.boardgames.model.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.tictactoe.TicTacToe;
import fr.boardgames.model.tictactoe.TicTacToeVisitor;
import fr.boardgames.view.ConsoleView;
import fr.boardgames.view.UserInteraction;

public class Main {
    public static void main(String[] args) {
        UserInteraction ui = new UserInteraction();
        ConsoleView view = new ConsoleView();

        int mode = ui.setUpGameMode();
        Player[] players = ui.setUpPlayers(mode);

        Game game = new TicTacToe(3, players[0], players[1]);
        TicTacToeVisitor visitor = new TicTacToeVisitor(view, ui);
        GameController controller = new GameController(game, visitor);
        controller.play();
    }
}