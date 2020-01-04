package top.chensmallx.android_harmony.model;

import com.google.gson.annotations.SerializedName;

public class GameDetail {
    @SerializedName(value="ID")
    private int id;
    @SerializedName(value="GameNameCN")
    private String gameNameCN;
    @SerializedName(value="GameNameEN")
    private String gameNameEN;
    @SerializedName(value="GameSize")
    private String gameSize;
    @SerializedName(value="ImgUrl")
    private String[] imageUrl;
    @SerializedName(value="Price")
    private GamePrice[] price;
    @SerializedName(value="LowestPrice")
    private GamePrice lowestPrice;
    @SerializedName(value="LanguageTag")
    private int[] languageTag;
    @SerializedName(value="HasSolidEdition")
    private boolean hasSolidEdition;
    @SerializedName(value="HasDemo")
    private boolean hasDemo;
    @SerializedName(value="IsExclusive")
    private boolean isExclusive;
    @SerializedName(value="GameTypeTag")
    private String[] gameTypeTag;
    @SerializedName(value="HasChinese")
    private boolean hasChinese;
    @SerializedName(value="GamePlayers")
    private int gamePlayers;
    @SerializedName(value="Description")
    private String description;
    @SerializedName(value="GameScore")
    private String[] gameScore;

    public GameDetail(int id, String gameNameCN, String gameNameEN, String gameSize, String[] imageUrl, GamePrice[] price, GamePrice lowestPrice, int[] languageTag, boolean hasSolidEdition, boolean hasDemo, boolean isExclusive, String[] gameTypeTag, boolean hasChinese, int gamePlayers, String description, String[] gameScore) {
        this.id = id;
        this.gameNameCN = gameNameCN;
        this.gameNameEN = gameNameEN;
        this.gameSize = gameSize;
        this.imageUrl = imageUrl;
        this.price = price;
        this.lowestPrice = lowestPrice;
        this.languageTag = languageTag;
        this.hasSolidEdition = hasSolidEdition;
        this.hasDemo = hasDemo;
        this.isExclusive = isExclusive;
        this.gameTypeTag = gameTypeTag;
        this.hasChinese = hasChinese;
        this.gamePlayers = gamePlayers;
        this.description = description;
        this.gameScore = gameScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameNameCN() {
        return gameNameCN;
    }

    public void setGameNameCN(String gameNameCN) {
        this.gameNameCN = gameNameCN;
    }

    public String getGameNameEN() {
        return gameNameEN;
    }

    public void setGameNameEN(String gameNameEN) {
        this.gameNameEN = gameNameEN;
    }

    public String getGameSize() {
        return gameSize;
    }

    public void setGameSize(String gameSize) {
        this.gameSize = gameSize;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GamePrice[] getPrice() {
        return price;
    }

    public void setPrice(GamePrice[] price) {
        this.price = price;
    }

    public GamePrice getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(GamePrice lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int[] getLanguageTag() {
        return languageTag;
    }

    public void setLanguageTag(int[] languageTag) {
        this.languageTag = languageTag;
    }

    public boolean isHasSolidEdition() {
        return hasSolidEdition;
    }

    public void setHasSolidEdition(boolean hasSolidEdition) {
        this.hasSolidEdition = hasSolidEdition;
    }

    public boolean isHasDemo() {
        return hasDemo;
    }

    public void setHasDemo(boolean hasDemo) {
        this.hasDemo = hasDemo;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }

    public String[] getGameTypeTag() {
        return gameTypeTag;
    }

    public void setGameTypeTag(String[] gameTypeTag) {
        this.gameTypeTag = gameTypeTag;
    }

    public boolean isHasChinese() {
        return hasChinese;
    }

    public void setHasChinese(boolean hasChinese) {
        this.hasChinese = hasChinese;
    }

    public int getGamePlayers() {
        return gamePlayers;
    }

    public void setGamePlayers(int gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getGameScore() {
        return gameScore;
    }

    public void setGameScore(String[] gameScore) {
        this.gameScore = gameScore;
    }
}
