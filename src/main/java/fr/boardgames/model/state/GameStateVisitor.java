package fr.boardgames.model.state;

import fr.boardgames.model.Game;

public interface GameStateVisitor { // définit une action à exécuter pour un certain état
    void visit (Game game); // chaque type de jeu a sa propre implémentation de ces visites
}
