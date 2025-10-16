package fr.boardgames.model.state;

public interface GameState { // Element = interface de base pour tous les jeux
    void accept(GameStateVisitor visitor);
}
