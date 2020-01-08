package top.chensmallx.android_harmony.service;

import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.http.GameHttpService;


@RunWith(AndroidJUnit4.class)
public class GameServiceTest {
    @Test
    public void testGetGameDetail() {
        GameService gameService = new GameService(ApplicationProvider.getApplicationContext());
        try {
            GameDetail detail = gameService.getGameDetailByID(1);
            Gson gson = new Gson();
            Log.println(Log.INFO, "T", gson.toJson(detail));
        }catch (Exception e) {
            Log.println(Log.ERROR, "HARMONY", e.getMessage());
        }
    }

    @Test
    public void testGetGameSummary() {
        GameService gameService = new GameService(ApplicationProvider.getApplicationContext());
        try {
            GameSummary  summary = gameService.getGameSummaryByID(1);
            Gson gson = new Gson();
            Log.println(Log.INFO, "HARMONY", gson.toJson(summary));
        }catch (Exception e) {
            Log.println(Log.ERROR, "HARMONY", e.getMessage());
        }
    }

    @Test
    public void testGetGames() {
        GameService gameService = new GameService(ApplicationProvider.getApplicationContext());
        try {
            List<GameSummary>  gameSummaries = gameService.getGameList(0,10);
            for (GameSummary g : gameSummaries) {
                Gson gson = new Gson();
                Log.println(Log.INFO, "HARMONY", gson.toJson(g));
            }
        }catch (Exception e) {
            Log.println(Log.ERROR, "HARMONY", e.getMessage());
        }
    }
    @Test
    public void testWishes() {
        GameSummary gameSummary = new GameSummary(122,  "xx", "xx", "xx", "xx", "xx" ,"xx" , new int[]{1, 2}, false, false, false);
        GameService gameService = new GameService(ApplicationProvider.getApplicationContext());
        gameService.addToWishList(gameSummary);
        List<GameSummary> results =  gameService.getWishGames(0, 100);
        for (int i = 0; i < results.size(); i++ ) {
            GameSummary g = results.get(i);
            Gson gson    = new Gson();
            Log.println(Log.INFO, "T", gson.toJson(g.getId()));
        }

    }

    @Test
    public void searchGame() {
        GameService gameService = new GameService(ApplicationProvider.getApplicationContext());
        try {
            List<GameSummary> results = gameService.searchGameByName("塞尔达", 0, 10);
            for (int i = 0; i < results.size(); i++) {
                GameSummary g = results.get(i);
                Gson gson = new Gson();
                Log.println(Log.INFO, "HARMONY", gson.toJson(g.getId()));
            }
        }catch (Exception e) {
            Log.println(Log.ERROR, "HARMONY", e.getMessage());
        }

    }
}




