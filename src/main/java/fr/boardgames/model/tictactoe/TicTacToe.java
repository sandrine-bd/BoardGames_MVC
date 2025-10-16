package fr.boardgames.model.tictactoe;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.ThreeInRowStrategy;

public class TicTacToe extends Game {
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

    public TicTacToeState getState() {
        return state;
    }

    public void setState(TicTacToeState state) {
        this.state = state;
    }
}
