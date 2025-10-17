package fr.boardgames.persistence;

import java.io.Serializable;

public class GameConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String lasPlayerName;
    private String preferredGameType;

    public GameConfig() {
        this.lasPlayerName = "";
        this.preferredGameType = "TicTacToe";
    }

    public String getLasPlayerName() {
        return lasPlayerName;
    }

    public void setLasPlayerName(String lasPlayerName) {
        this.lasPlayerName = lasPlayerName;
    }

    public String getPreferredGameType() {
        return preferredGameType;
    }

    public void setPreferredGameType(String preferredGameType) {
        this.preferredGameType = preferredGameType;
    }
}
