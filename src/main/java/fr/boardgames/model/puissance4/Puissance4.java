package fr.boardgames.model.puissance4;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.FourInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Règles du jeu Puissance 4
 */
public class Puissance4 extends Game {
    private static final long serialVersionUID = 1L;

    private Puissance4State state;

    /**
     * Constructeur basé sur 2 joueurs
     * @param player1 joueur 1
     * @param player2 joueur 2
     */
    public Puissance4(Player player1, Player player2) {
        super(new int[]{6, 7}, player1, player2, new FourInRowStrategy());
        this.state = Puissance4State.INIT;
        initializeBoard();
    }

    /**
     * Vérifie si le jeu est terminé
     * @return l'état du jeu "Game Over"
     */
    @Override
    public boolean isGameOver() {
        return state == Puissance4State.GAME_OVER;
    }

    /**
     * Initialise le jeu avec la stratégie du Puissance 4 (4 cases à aligner)
     */
    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new FourInRowStrategy();
    }

    /**
     * Méthode spécifique au Puissance 4 car les coordonnées choisies doivent descendre en bas de la grille
     * @param row numéro de ligne choisie par le joueur
     * @param col numéro de colonne choisie par le joueur
     * @param player le joueur en cours
     */
    @Override
    public void setOwner(int row, int col, Player player) {
        // on ignore row
        for (int r = size[0] - 1 ; r >= 0; r--) { // on part dans la colonne du bas vers le haut
            if (board[r][col].isEmpty()) { // cherche la 1ère case vide
                board[r][col].setSymbol(player.getSymbol()); // on pose le pion
                return;
            }
        }
        System.out.println("Colonne " + col + "pleine ! Choisissez-en une autre."); // si aucune case vide
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    // Getter
    public Puissance4State getState() { return state; }
    // Setter
    public void setState(Puissance4State state) { this.state = state; }
}
