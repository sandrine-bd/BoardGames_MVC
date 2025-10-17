package fr.boardgames.model.tictactoe;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.ThreeInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Règles du jeu Tic Tac Toe
 */
public class TicTacToe extends Game {
    private static final long serialVersionUID = 1L;

    private TicTacToeState state;

    public TicTacToe(Player player1, Player player2) {
        super(new int[]{3, 3}, player1, player2, new ThreeInRowStrategy());
        this.state = TicTacToeState.INIT;
        initializeBoard();
    }

    /**
     * Vérifie si le jeu est terminé
     * @return l'état du jeu "Game Over"
     */
    @Override
    public boolean isGameOver() {
        return state == TicTacToeState.GAME_OVER;
    }
    /**
     * Initialise le jeu avec la stratégie du Tic Tac Toe (3 cases à aligner)
     */
    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new ThreeInRowStrategy();
    }

    // Méthode appelée après désérialisation
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    // Getter
    public TicTacToeState getState() {
        return state;
    }
    // Setter
    public void setState(TicTacToeState state) {
        this.state = state;
    }
}
