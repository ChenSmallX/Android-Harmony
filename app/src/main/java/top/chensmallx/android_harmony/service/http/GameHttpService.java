package top.chensmallx.android_harmony.service.http;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummartList;
import top.chensmallx.android_harmony.model.GameSummary;

public class GameHttpService {

    private static final String DOMAIN = "http://maymomo.cn:8080";
    private OkHttpClient client;

    public GameHttpService() {
        client = new OkHttpClient();
    }

    private <T> T  get(String url, Class<T> classOfT) throws IOException, JsonParseException {
        Request request = new Request.Builder().url(url).build();
        String rsp;
        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 ) {
                throw new IOException("http error, code = " + response.code());
            }
            rsp = response.body().string();
        }
        Gson gson = new Gson();
        return gson.fromJson(rsp, classOfT);
    }

    public List<GameSummary> searchByName(String game, int offset, int limit) throws IOException, JsonParseException {
        return get(DOMAIN + "/game/" + "?" + "limit=" + limit + "&" +"offset=" + offset + "&" + "q=" + game, GameSummartList.class).getGameSummaryList();
    }

    public GameDetail getGameDetail(int id) throws IOException, JsonParseException {
        return get(DOMAIN+"/game/" + id +"/detail", GameDetail.class);
    }

    public GameSummary getGameSummary(int id) throws IOException, JsonParseException {
        return get(DOMAIN+"/game/" + id + "/summary", GameSummary.class);
    }

    public List<GameSummary> getGameList(int offset, int limit) throws IOException, JsonParseException {
        GameSummartList gameSummartList = get(DOMAIN + "/game?" +"limit=" + limit + "&" +"offset=" + offset, GameSummartList.class);
        if (gameSummartList == null) {
            return null;
        }
        return gameSummartList.getGameSummaryList();
    }
}

