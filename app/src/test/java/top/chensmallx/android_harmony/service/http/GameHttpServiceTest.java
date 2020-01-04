package top.chensmallx.android_harmony.service.http;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import java.util.StringJoiner;

import top.chensmallx.android_harmony.model.GameDetail;

import static org.junit.Assert.assertEquals;

public class GameHttpServiceTest {
    @Test
    public void testGetGameDetail() {
        GameHttpService service = new GameHttpService();
        try {
            GameDetail detail = service.getGameDetail(1);
            Gson gson = new Gson();
            System.out.println(gson.toJson(detail));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}


