package fr.boardgames.model.strategy;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;

/**
 * Interface qui vérifie si un jeu est gagné
 */
public interface WinStrategy {
    boolean checkWin(Game game, Player player);
}
