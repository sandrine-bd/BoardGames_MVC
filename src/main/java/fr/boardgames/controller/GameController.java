package fr.boardgames.controller;

import fr.boardgames.model.Game;
import fr.boardgames.model.state.GameStateVisitor;

public class GameController {
    private final Game game;
    private GameStateVisitor visitor;

    public GameController(Game game, GameStateVisitor visitor) {
        this.game = game;
        this.visitor = visitor;
    }

    public void play() {
        // visite initiale
        game.accept(visitor);

        // boucle principale
        while (!game.isGameOver()) {
            game.accept(visitor);
        }

        // visite finale pour afficher l'état GAME_OVER
        game.accept(visitor);
    }
}