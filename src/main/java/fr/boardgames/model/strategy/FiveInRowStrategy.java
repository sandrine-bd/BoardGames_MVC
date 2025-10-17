package fr.boardgames.model.strategy;

/**
 * Nombre de cases à aligner pour gagner au Gomoku
 */
public class FiveInRowStrategy extends AlignmentStrategy {
    public FiveInRowStrategy() {
        super(5);
    }

    /**
     * Règle standard du Gomoku : aligner EXACTEMENT 5 cases
     * @param count nombre de cases à aligner pour gagner
     * @return true si l'alignement du joueur est exactement égal à ce nombre
     */
    @Override
    protected boolean isWinningAlignment(int count) {
        return count == alignmentNeeded;
    }
}
