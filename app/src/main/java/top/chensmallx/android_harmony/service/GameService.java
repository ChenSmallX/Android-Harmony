package top.chensmallx.android_harmony.service;

import android.content.Context;

import androidx.room.Room;

import java.io.IOException;
import java.util.List;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.db.HarmonyDB;
import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.http.GameHttpService;


public class GameService {

    // 心愿单管理

    // 本地数据库管理
    private static   HarmonyDB harmonyDB;

    private GameHttpService gameHttpService;

    private Context appContext;

    public GameService(Context context) {
        gameHttpService = new GameHttpService();
        appContext = context.getApplicationContext();
    }


    private HarmonyDB getHarmonyDB() {
        synchronized (this) {
            if (harmonyDB == null) {
                 harmonyDB = Room.databaseBuilder(appContext,
                        HarmonyDB.class, HarmonyDB.DB).build();
            }
        }
        return harmonyDB;
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
        WishDao wishDao = getHarmonyDB().wishDao();
        return wishDao.getByLimit(offset, len);
    }

    // 访问本地数据库
    public void addToWishList(GameSummary gameSummary) {
        WishDao wishDao = getHarmonyDB().wishDao();
        wishDao.insertAll(gameSummary);
    }
}
