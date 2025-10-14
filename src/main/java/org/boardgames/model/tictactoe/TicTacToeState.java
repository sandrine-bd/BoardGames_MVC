package org.boardgames.model.tictactoe;

import org.boardgames.model.state.GameState;
import org.boardgames.model.state.GameStateVisitor;

public enum TicTacToeState implements GameState {
    INIT,
    PLAYER_TURN,
    CHECK_WIN,
    GAME_OVER;

    @Override
    public void accept(GameStateVisitor visitor) {
        visitor.visit(null); // ici, on passera plus tard le jeu en paramètre
    }
}