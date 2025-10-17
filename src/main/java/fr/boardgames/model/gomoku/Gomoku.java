package fr.boardgames.model.gomoku;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.FiveInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Gomoku extends Game {
    private static final long serialVersionUID = 1L;

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

    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new FiveInRowStrategy();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    public GomokuState getState() {
        return state;
    }

    public void setState(GomokuState state) {
        this.state = state;
    }
}
