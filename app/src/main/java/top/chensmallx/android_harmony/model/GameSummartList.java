package top.chensmallx.android_harmony.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameSummartList {
    @SerializedName(value = "List")
    private List<GameSummary> gameSummaryList;

    public GameSummartList(List<GameSummary> gameSummaryList) {
        this.gameSummaryList = gameSummaryList;
    }

    public List<GameSummary> getGameSummaryList() {
        return gameSummaryList;
    }

    public void setGameSummaryList(List<GameSummary> gameSummaryList) {
        this.gameSummaryList = gameSummaryList;
    }
}
