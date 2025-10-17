package fr.boardgames.persistence;

import java.io.Serializable;

public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lastPlayerName;
    private String preferredGameType;

    public GameConfig() {
        this.lastPlayerName = "";
        this.preferredGameType = "TicTacToe";
    }

    public String getLastPlayerName() {
        return lastPlayerName;
    }

    public void setLastPlayerName(String lasPlayerName) {
        this.lastPlayerName = lasPlayerName;
    }

    public String getPreferredGameType() {
        return preferredGameType;
    }

    public void setPreferredGameType(String preferredGameType) {
        this.preferredGameType = preferredGameType;
    }
}
