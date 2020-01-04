package top.chensmallx.android_harmony.service.http;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.chensmallx.android_harmony.model.GameDetail;
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

    public GameDetail getGameDetail(int id) throws IOException, JsonParseException {
        return get(DOMAIN+"/info/detail/"+id, GameDetail.class);
    }

    public GameSummary getGameSummary(int id) throws IOException, JsonParseException {
        return get(DOMAIN+"/info/summary/"+id, GameSummary.class);
    }
}

