package fr.boardgames.model.strategy;

public class FiveInRowStrategy extends AlignmentStrategy {
    public FiveInRowStrategy() {
        super(5);
    }

    @Override
    protected boolean isWinningAlignment(int count) {
        // règle standard du Gomoku : EXACTEMENT 5
        return count == alignmentNeeded;
    }
}
