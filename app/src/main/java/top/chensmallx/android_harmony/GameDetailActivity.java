package top.chensmallx.android_harmony;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GamePrice;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.GameService;

public class GameDetailActivity extends AppCompatActivity {

    private GameDetail gameDetail;
    private GameSummary gameSummary;

    private int gameId;

    private Button gameDetailLikeButton;

    private ImageView gameDetailMainPhoto;
    private TextView gameDetailNameChinese;
    private TextView gameDetailNameEnglish;
    private TextView gameDetailPriceCurr;
    private TextView gameDetailUpdate;
    private TextView gameDetailIntroShort;
    private TextView gameDetailRegion;
    private TextView gameDetailLanguage;
    private TextView gameDetailSize;
    private TextView gameDetailPlayers;
    private TextView gameDetailHasSolid;
    private TextView gameDetailHasDemo;
    private TextView gameDetailRecommendRate;

    private TextView gameDetailLowestPrice;

    private TextView gameDetailIntroLong;

    private boolean liked = false;

    RecyclerView recyclerView;

    MyHandler handler;
    ImgHandler imgHandler;

    PriceAdapter adapter;

    List<Bitmap> bitmapList;
    Bitmap bitbitmap = null;
    List<String> urlList;

    GameService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_detail);

        service = new GameService(getApplicationContext());

        gameDetailMainPhoto = (ImageView) findViewById(R.id.game_detail_main_photo);
        gameDetailNameChinese = (TextView) findViewById(R.id.game_detail_name_chinese);
        gameDetailNameEnglish = (TextView) findViewById(R.id.game_detail_name_english);
        gameDetailPriceCurr = (TextView) findViewById(R.id.game_item_price_curr);
        gameDetailUpdate = (TextView) findViewById(R.id.game_detail_order_date);
//        gameDetailIntroShort = (TextView) findViewById(R.id.game_detail_intro_short);
        gameDetailRegion = (TextView) findViewById(R.id.game_detail_region);
        gameDetailLanguage = (TextView) findViewById(R.id.game_detail_language);
        gameDetailSize = (TextView) findViewById(R.id.game_detail_size);
        gameDetailPlayers = (TextView) findViewById(R.id.game_detail_players);
        gameDetailHasSolid = (TextView) findViewById(R.id.game_detail_has_solid);
        gameDetailHasDemo = (TextView) findViewById(R.id.game_detail_has_demo);
        gameDetailLowestPrice = (TextView) findViewById(R.id.game_detail_price_history_lowest);
        gameDetailIntroLong = (TextView) findViewById(R.id.game_detail_intro_long);
        gameDetailRecommendRate = (TextView)findViewById(R.id.game_detail_recommend_rate);

        gameDetailLikeButton = (Button) findViewById(R.id.game_detail_like_button);
        gameDetailLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        service.addToWishList(gameSummary);
                        Message message = Message.obtain();
                        message.what = MyHandler.BUTTON_CLICK;

                        handler.sendMessage(message);
                    }
                }.start();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.game_detail_low_price_rank_recycle);

        bitmapList = new ArrayList<>();
        urlList = new ArrayList<>();


        gameId = (int)getIntent().getSerializableExtra("gameId");
        Log.e("GameDetailActivity", "get game id = "+gameId);
        this.setTitle("游戏详情");

        gameDetail = null;
        handler = new MyHandler();
        imgHandler = new ImgHandler();

        new Thread() {
            @Override
            public void run() {
                GameDetail detail = null;
                GameService service = new GameService(getApplicationContext());
                try {
                    detail = service.getGameDetailByID(gameId);
                    gameSummary = service.getGameSummaryByID(gameId);
                    if (gameSummary != null) {
                        Log.e("DETAIL", "get summary");
                    }
                } catch (IOException e) {
                    Log.e("IOE", e.toString());
                }

                List<GameSummary> gameSummaryList = service.getWishGames(0, 100);
                for (int i = 0; i < gameSummaryList.size(); i++) {
                    if (gameId == gameSummaryList.get(i).getId()) {
                        liked = true;
                    }
                }
                if (detail != null) {
                    gameDetail = detail;
                    Message message = Message.obtain();
                    message.what = FragmentHome.MyHandler.INIT_DATA;
                    message.obj = detail;

                    handler.sendMessage(message);
                }
            }
        }.start();

    }

    class MyHandler extends Handler {

        final static public int INIT_DATA = 1;
        final static public int RENEW_DATA = 2;
        final static public int UPDATE_DATA = 3;
        final static public int BUTTON_CLICK = 4;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_DATA:

                    GameDetail detail = (GameDetail) msg.obj;
                    gameDetailNameChinese.setText(detail.getGameNameCN());
                    gameDetailNameEnglish.setText(detail.getGameNameEN());
                    gameDetailSize.setText(gameDetail.getGameSize());
                    gameDetailLowestPrice.setText(""+gameDetail.getPrice()[0].getHistoryLowestPrice());
                    gameDetailHasSolid.setText(gameDetail.isHasSolidEdition()?"有":"无");
                    gameDetailHasDemo.setText(gameDetail.isHasDemo()?"有":"无");
                    gameDetailPlayers.setText(""+gameDetail.getGamePlayers());
                    gameDetailIntroLong.setText(gameDetail.getDescription());
                    gameDetailUpdate.setText("");

                    List<GamePrice> l = new ArrayList<>(Arrays.asList(gameDetail.getPrice()));
                    adapter = new PriceAdapter(l);

                    if (recyclerView != null) {
                        recyclerView.setHasFixedSize(true);final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext()); // final 修饰其被赋值后无法被改变
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }

                    String[] score = gameDetail.getGameScore();
                    String sscore = new String("");
                    if (score == null || score.length == 0) {
                        sscore = "无";
                    } else {
                        sscore = sscore + score[0];
                        for (int i = 1; i < score.length; i++) {
                            sscore = sscore + " / " + score[i];
                        }
                    }
                    gameDetailRecommendRate.setText(sscore);

                    if (liked) {
                        gameDetailLikeButton.setText("已收藏");
                        gameDetailLikeButton.setClickable(false);
                    }

                    urlList = Arrays.asList(gameDetail.getImageUrl());

                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                URL url = new URL(urlList.get(0));
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("GET");
                                connection.setConnectTimeout(10000);
                                int code = connection.getResponseCode();
                                if (code == 200) {
                                    InputStream inputStream = connection.getInputStream();
                                    //使用工厂把网络的输入流生产Bitmap
                                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                    //利用Message把图片发给Handler
                                    Message msg = Message.obtain();
                                    msg.what = ImgHandler.GET_IMAGE_SUC;
                                    msg.obj = bitmap;
                                    inputStream.close();
                                    if (bitmap != null) {
                                        Log.e("IMG", "get img");
                                    }
                                    imgHandler.sendMessage(msg);
                                }
                            } catch (IOException e) {
                                Log.e("IMG", e.toString());
                                //网络连接错误
                            }
                        }
                    }.start();

                    break;
                case BUTTON_CLICK:
                    gameDetailLikeButton.setText("已收藏");
                    gameDetailLikeButton.setClickable(false);
                    break;
            }
        }
    }

    class ImgHandler extends Handler {
        final static public int GET_IMAGE_SUC = 4;
        final static public int LAST_IMAGE = 5;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LAST_IMAGE:
                case GET_IMAGE_SUC:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    bitbitmap = (Bitmap) msg.obj;
                    bitmapList.add(bitmap);
                    gameDetailMainPhoto.setImageBitmap(bitbitmap);
                    break;
            }
        }
    }



    static class PriceAdapter extends RecyclerView.Adapter<PriceViewHolder> {

        private List<GamePrice> gamePriceList;

        public PriceAdapter(List<GamePrice> data) {
            this.gamePriceList = data;
        }

        @Override
        public PriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.price_item, parent, false);
            PriceViewHolder viewHolder = new PriceViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final PriceViewHolder holder, int position) {
            holder.priceRegion.setText(gamePriceList.get(position).getRegion());
            holder.priceSaleRate.setText(""+gamePriceList.get(position).getSaleRate()+"%Off");
            holder.pricePrice.setText(""+gamePriceList.get(position).getPriceCNY()+"CNY");
        }

        @Override
        public int getItemCount() {
            return gamePriceList == null ? 0 : gamePriceList.size();
        }


    }







    static class PriceViewHolder extends RecyclerView.ViewHolder {

        public TextView priceRegion;
        public TextView priceSaleRate;
        public TextView pricePrice;

        public PriceViewHolder(View view) {
            super(view);

            priceRegion = (TextView) view.findViewById(R.id.price_item_region);
            priceSaleRate = (TextView) view.findViewById(R.id.price_item_sale_rate);
            pricePrice = (TextView) view.findViewById(R.id.price_item_price);

        }

    }

}
