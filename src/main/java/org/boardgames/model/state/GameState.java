package org.boardgames.model.state;

public interface GameState {
    void accept (GameStateVisitor visitor); // chaque état peut accepter un visiteur qui applique la logique du jeu
}
