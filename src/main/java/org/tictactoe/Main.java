package org.tictactoe;

import org.tictactoe.controller.GameController;
import org.tictactoe.model.game.Game;
import org.tictactoe.model.player.Player;
import org.tictactoe.model.game.TicTacToe;
import org.tictactoe.view.ConsoleView;
import org.tictactoe.view.UserInteraction;

public class Main {
    public static void main(String[] args) {
        UserInteraction ui = new UserInteraction();
        ConsoleView view = new ConsoleView();

        int mode = ui.setUpGameMode();
        Player[] players = ui.setUpPlayers(mode);

        Game game = new TicTacToe(3, players[0], players[1]);
        GameController controller = new GameController(game, view, ui);
        controller.play();
    }
}