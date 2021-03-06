package top.chensmallx.android_harmony.service;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import top.chensmallx.android_harmony.dao.WishDao;
import top.chensmallx.android_harmony.db.HarmonyDB;
import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.http.GameHttpService;


public class GameService {

    // 心愿单管理

    // 本地数据库管理
    private static  volatile HarmonyDB harmonyDB;

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
    public List<GameSummary> searchGameByName(String name, int offset, int limit) throws  IOException{
        return gameHttpService.searchByName(name, offset, limit);
    }

    // 获取游戏详情
    public GameDetail getGameDetailByID(int id) throws IOException {
        return gameHttpService.getGameDetail(id);
    }

    public GameSummary getGameSummaryByID(int id) throws IOException {
        return gameHttpService.getGameSummary(id);
    }
    // 首页折扣游戏
    public List<GameSummary> getGameList(int offset, int limit) throws IOException {
        return gameHttpService.getGameList(offset, limit);
    }

    // 访问本地数据库
    public List<GameSummary> getWishGames(int offset, int limit) {
        WishDao wishDao = getHarmonyDB().wishDao();
        List<GameSummary> gameSummaries = wishDao.getByLimit(offset, limit);
        for (GameSummary g : gameSummaries) {
            if (System.currentTimeMillis() >= g.getUpdateAt() + (1000 * 60 * 6)) {
                continue;
            }
            try {
                g = getGameSummaryByID(g.getId());
                g.setUpdateAt(System.currentTimeMillis());
                wishDao.update(g);
            } catch (Exception e) {
                Log.e("HARMONY", e.getMessage());
            }
        }
        return gameSummaries;
    }

    // 访问本地数据库
    public void addToWishList(GameSummary gameSummary) {
        WishDao wishDao = getHarmonyDB().wishDao();
        gameSummary.setUpdateAt(System.currentTimeMillis());
        if (null == wishDao.findById(gameSummary.getId())){
            wishDao.insertAll(gameSummary);
        }
    }
}
