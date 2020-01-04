package top.chensmallx.android_harmony.service;

import com.google.gson.Gson;

import org.junit.Test;

import top.chensmallx.android_harmony.model.GameSummary;



public class GameSummaryTest {
    @Test
    public void testGameSummary() {
        GameSummary g = new GameSummary(1, "xx", "xx", "xx", "xx", "xx", "xx", new int[]{1, 2}, false, false, false);
        Gson gson = new Gson();
        GameSummary gx = gson.fromJson(gson.toJson(g), GameSummary.class);
        System.out.println(gson.toJson(gx));
    }
}
