package fr.boardgames.model.player;

/**
 * Joueur artificiel qui possède un nom et un symbole
 */
public class ArtificialPlayer extends Player {
    public ArtificialPlayer(String name, String symbol) {
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
