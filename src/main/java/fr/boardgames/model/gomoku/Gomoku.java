package fr.boardgames.model.gomoku;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.FiveInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Règles du jeu Gomoku
 */
public class Gomoku extends Game {
    private static final long serialVersionUID = 1L;

    private GomokuState state;

    /**
     * Constructeur basé sur 2 joueurs
     * @param player1 joueur 1
     * @param player2 joueur 2
     */
    public Gomoku(Player player1, Player player2) {
        super(new int[]{15, 15}, player1, player2, new FiveInRowStrategy());
        this.state = GomokuState.INIT;
        initializeBoard();
    }

    /**
     * Vérifie si le jeu est terminé
     * @return l'état du jeu "Game Over"
     */
    @Override
    public boolean isGameOver() {
        return state == GomokuState.GAME_OVER;
    }

    /**
     * Initialise le jeu avec la stratégie du Gomoku (5 cases à aligner)
     */
    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new FiveInRowStrategy();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    // Getter
    public GomokuState getState() { return state; }
    // Setter
    public void setState(GomokuState state) { this.state = state; }
}
