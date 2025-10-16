package fr.boardgames.model.state;

import fr.boardgames.model.Game;

public interface GameStateVisitor { // interface Visitor
    void visit(Game game); // chaque type de jeu a sa propre implémentation de ces visites
}
