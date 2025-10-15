package fr.boardgames.model.state;

import fr.boardgames.model.Game;

public interface GameState {
    void accept (GameStateVisitor visitor, Game game); // chaque état peut accepter un visiteur qui applique la logique du jeu
}
