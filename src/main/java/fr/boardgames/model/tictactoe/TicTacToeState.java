package fr.boardgames.model.tictactoe;

import fr.boardgames.model.Game;
import fr.boardgames.model.state.GameState;
import fr.boardgames.model.state.GameStateVisitor;

public enum TicTacToeState implements GameState {
    INIT,
    PLAYER_TURN,
    CHECK_WIN,
    GAME_OVER;

    @Override
    public void accept(GameStateVisitor visitor, Game game) {
        visitor.visit(game);
    }
}