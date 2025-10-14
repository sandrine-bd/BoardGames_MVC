package org.boardgames;

import org.boardgames.controller.GameController;
import org.boardgames.model.Game;
import org.boardgames.model.player.Player;
import org.boardgames.model.tictactoe.TicTacToe;
import org.boardgames.view.ConsoleView;
import org.boardgames.view.UserInteraction;

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