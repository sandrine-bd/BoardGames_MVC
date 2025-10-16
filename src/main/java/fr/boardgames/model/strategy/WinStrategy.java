package fr.boardgames.model.strategy;

import fr.boardgames.model.game.Game;
import fr.boardgames.model.player.Player;

public interface WinStrategy {
    boolean checkWin(Game game, Player player);
}
