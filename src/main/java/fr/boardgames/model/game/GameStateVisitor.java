package fr.boardgames.model.game;

/**
 * Interface Visitor
 * Chaque type de jeu a sa propre implémentation de ces visites
 */
public interface GameStateVisitor {
    void visit(Game game); //
}
