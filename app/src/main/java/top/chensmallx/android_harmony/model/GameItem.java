package top.chensmallx.android_harmony.model;

public class GameItem {

    private String gameNameChinese;
    private String gameNameEnglish;
    private String gamePriceCurr;
    private String gamePriceOri;
    private String gamePriceCoin = "CNY";

    private String gameCoverUrl;


    public GameItem() {
        super();
    }

    public GameItem(String gameNameChinese,
                    String gameNameEnglish,
                    String gamePriceCurr,
                    String gamePriceOri,
                    String gamePriceCoin,
                    String gameCoverUrl) {
        super();

        this.gameNameChinese = gameNameChinese;
        this.gameNameEnglish = gameNameEnglish;
        this.gamePriceCurr = gamePriceCurr;
        this.gamePriceOri = gamePriceOri;
        this.gamePriceCoin = gamePriceCoin;
        this.gameCoverUrl = gameCoverUrl;
    }
}
