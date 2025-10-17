package fr.boardgames.model.puissance4;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;
import fr.boardgames.model.strategy.FourInRowStrategy;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Puissance4 extends Game {
    private static final long serialVersionUID = 1L;

    private Puissance4State state;

    public Puissance4(Player player1, Player player2) {
        super(new int[]{6, 7}, player1, player2, new FourInRowStrategy());
        this.state = Puissance4State.INIT;
        initializeBoard();
    }

    @Override
    public boolean isGameOver() {
        return state == Puissance4State.GAME_OVER;
    }

    @Override
    protected void reinitializeStrategy() {
        this.winStrategy = new FourInRowStrategy();
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        reinitializeStrategy();
    }

    public Puissance4State getState() {
        return state;
    }

    public void setState(Puissance4State state) {
        this.state = state;
    }

    // Méthode spécifique au Puissance 4
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
}
