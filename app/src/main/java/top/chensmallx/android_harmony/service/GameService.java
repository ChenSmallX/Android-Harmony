package top.chensmallx.android_harmony.service;

import android.content.Context;

import java.util.List;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.db.HarmonyDBHelper;
import top.chensmallx.android_harmony.model.GameItem;
import top.chensmallx.android_harmony.model.GameDetail;

public class GameService {

    // 心愿单管理
    private WishDao wishDao;

    // 本地数据库管理
    private HarmonyDBHelper harmonyDBHelper;

    public GameService(Context context) {
        harmonyDBHelper = new HarmonyDBHelper(context);
        wishDao = new WishDao(harmonyDBHelper);
    }

    // 搜索游戏
    public List<GameItem> searchGameByName(String name, int offset, int len) {
        return null;
    }

    // 获取游戏详情
    public GameDetail getGameDetailByID(int id) {
        return null;
    }

    // 首页折扣游戏
    public List<GameItem> getOffGame(int offset, int len) {
        return null;
    }

    // 访问本地数据库
    public List<GameItem> getWishGames(int offset, int len) {
        return null;
    }

    // 访问本地数据库
    public void addToWishList(GameItem item) {
    }
}
