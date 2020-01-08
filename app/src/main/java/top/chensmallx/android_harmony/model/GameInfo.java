package top.chensmallx.android_harmony.model;

import com.google.gson.annotations.SerializedName;

public class GameInfo {
    @SerializedName(value="GameSummary")
    private GameSummary gameSummary;
    @SerializedName(value="GameDetail")
    private GameDetail gameDetail;

    public GameInfo(GameSummary gameSummary, GameDetail gameDetail) {
        this.gameSummary = gameSummary;
        this.gameDetail = gameDetail;
    }

    public GameSummary getGameSummary() {
        return gameSummary;
    }

    public void setGameSummary(GameSummary gameSummary) {
        this.gameSummary = gameSummary;
    }

    public GameDetail getGameDetail() {
        return gameDetail;
    }

    public void setGameDetail(GameDetail gameDetail) {
        this.gameDetail = gameDetail;
    }
}
