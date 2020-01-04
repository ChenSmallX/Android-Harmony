package top.chensmallx.android_harmony.service;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.db.HarmonyDBHelper;
import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.model.GamePrice;
import top.chensmallx.android_harmony.service.http.GameHttpService;


public class GameService {

    // 心愿单管理
    private WishDao wishDao;

    // 本地数据库管理
    private HarmonyDBHelper harmonyDBHelper;

    private GameHttpService gameHttpService;



    public GameService(Context context) {
        harmonyDBHelper = new HarmonyDBHelper(context);
        wishDao = new WishDao(harmonyDBHelper);
        gameHttpService = new GameHttpService();
    }


    // 搜索游戏
    public List<GameSummary> searchGameByName(String name, int offset, int len) {
        return null;
    }

    // 获取游戏详情
    public GameDetail getGameDetailByID(int id) throws IOException {
        return gameHttpService.getGameDetail(id);
    }

    // 首页折扣游戏
    public List<GameSummary> getOffGame(int offset, int len) {
        return null;
    }

    // 访问本地数据库
    public List<GameSummary> getWishGames(int offset, int len) {
        return null;
    }

    // 访问本地数据库
    public void addToWishList(GameSummary item) {
    }
}
