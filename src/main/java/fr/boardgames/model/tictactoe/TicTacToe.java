package fr.boardgames.model.tictactoe;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.ThreeInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TicTacToe extends Game {
    private static final long serialVersionUID = 1L;

    private TicTacToeState state;

    public TicTacToe(Player player1, Player player2) {
        super(new int[]{3, 3}, player1, player2, new ThreeInRowStrategy());
        this.state = TicTacToeState.INIT;
        initializeBoard();
    }

    @Override
    public boolean isGameOver() {
        return state == TicTacToeState.GAME_OVER;
    }

    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new ThreeInRowStrategy();
    }

    // Méthode appelée après désérialisation
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    public TicTacToeState getState() {
        return state;
    }

    public void setState(TicTacToeState state) {
        this.state = state;
    }
}
