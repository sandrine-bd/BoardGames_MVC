package fr.boardgames.model.tictactoe;

import fr.boardgames.model.Game;
import fr.boardgames.model.player.Player;

public class TicTacToe extends Game {
    private TicTacToeState state;

    public TicTacToe(int[] size, Player player1, Player player2) {
        super(size, player1, player2);
        this.state = TicTacToeState.INIT;
        initializeBoard();
    }

    @Override
    public boolean isWinner(Player player) {
        String symbol = player.getSymbol();

        // lignes
        for (int i = 0; i < size[0]; i++) {
            boolean rowWin = true;
            for (int j = 0; j < size[1]; j++) {
                if (!board[i][j].getSymbol().equals(symbol)) {
                    rowWin = false;
                    break;
                }
            } if (rowWin) return true;
        }

        // colonnes
        for (int j = 0; j < size[1]; j++) {
            boolean colWin = true;
            for (int i = 0; i < size[0]; i++) {
                if (!board[i][j].getSymbol().equals(symbol)) {
                    colWin = false;
                    break;
                }
            } if (colWin) return true;
        }

        // diagonale droite (haut-gauche → bas-droit)
        boolean diag1Win = true;
        for (int i = 0; i < size[0]; i++) {
            if (!board[i][i].getSymbol().equals(symbol)) {
                diag1Win = false;
                break;
            }
        } if (diag1Win) return true;

        // diagonale gauche (haut-droit → bas-gauche)
        boolean diag2Win = true;
        for (int i = 0; i < size[0]; i++) {
            if (!board[i][size[0] - 1 - i].getSymbol().equals(symbol)) {
                diag2Win = false;
                break;
            }
        } if (diag2Win) return true;

        return false;
    }

    @Override
    public boolean isGameOver() {
        return state == TicTacToeState.GAME_OVER;
    }

    public TicTacToeState getState() {
        return state;
    }

    public void setState(TicTacToeState state) {
        this.state = state;
    }
}
