package fr.boardgames.model.player;

/**
 * Joueur humain qui possède un nom et un symbole
 */
public class HumanPlayer extends Player {
    public HumanPlayer(String name, String symbol) {
        super(name, symbol);
    }

    /**
     * Affichage du joueur
     * @return son symbole
     */
    @Override
    public String getSymbol() {
        return super.getSymbol();
    }
}

