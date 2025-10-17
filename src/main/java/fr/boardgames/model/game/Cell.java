package fr.boardgames.model.game;

import java.io.Serializable;

/**
 * Classe sérializable qui construit les cases de la grille d'un jeu
 */
public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;

    private String symbol;

    /**
     * Constructeur qui définit chaque case comme vide par défaut
     */
    public Cell() { this.symbol = "   "; }

    /**
     * Méthode pour vérifier si une case est vide
     * @return le contenu vide d'une case
     */
    public boolean isEmpty() {
        return symbol.equals("   ");
    }

    /**
     * @return le symbole contenu de la case
     */
    @Override
    public String toString() { return getSymbol(); }

    // Getter
    public String getSymbol() { return symbol; }
    // Setter
    public void setSymbol(String symbol) { this.symbol = symbol; }
}
