package fr.boardgames.model.game;

public interface GameStateVisitor { // interface Visitor
    void visit(Game game); // chaque type de jeu a sa propre implémentation de ces visites
}
