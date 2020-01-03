package top.chensmallx.android_harmony.Item;

public class GameItem {

    private String game_name_chinese;
    private String game_name_english;
    private String game_price_curr;
    private String game_price_ori;
    private String game_price_coin = "CNY";

    private String game_cover_url;


    public GameItem() {
        super();
    }

    public GameItem(String game_name_chinese_,
                    String game_name_english_,
                    String game_price_curr_,
                    String game_price_ori_,
                    String game_price_coin_,
                    String game_cover_url_) {
        super();

        game_name_chinese   = game_name_chinese_;
        game_name_english   = game_name_english_;
        game_price_curr     = game_price_curr_;
        game_price_ori      = game_price_ori_;
        game_price_coin     = game_price_coin_;
        game_cover_url      = game_cover_url_;
    }
}
