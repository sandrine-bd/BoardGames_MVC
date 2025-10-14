package org.boardgames.controller;

import org.boardgames.model.Game;
import org.boardgames.model.state.GameStateVisitor;

public class GameController {
    private final Game game;
    private GameStateVisitor visitor;

    public GameController(Game game, GameStateVisitor visitor) {
        this.game = game;
        this.visitor = visitor;
    }

    public void play() {
        boolean running = true;
        while (running) {
            game.getState().accept(visitor);
            if (game.isWinner(game.currentPlayer) || game.isBoardFull()) running = false;
        }
    }
}
