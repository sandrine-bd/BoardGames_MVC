package fr.boardgames.model.gomoku;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.FiveInRowStrategy;

public class Gomoku extends Game {
    private GomokuState state;

    public Gomoku(Player player1, Player player2) {
        super(new int[]{15, 15}, player1, player2, new FiveInRowStrategy());
        this.state = GomokuState.INIT;
        initializeBoard();
    }

    @Override
    public boolean isGameOver() {
        return state == GomokuState.GAME_OVER;
    }

    public GomokuState getState() {
        return state;
    }

    public void setState(GomokuState state) {
        this.state = state;
    }
}
