package top.chensmallx.android_harmony;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import top.chensmallx.android_harmony.model.GameDetail;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.GameService;

public class FragmentMe extends Fragment {

    private GameService gameService;

    private int offset = 0;
    private int limit = 50;

    private RecyclerView recyclerView;
    GameSumItemAdapter adapter;

    private List<GameSummary> gameSummaries = null;

    MyHandler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameService = new GameService(getActivity().getApplicationContext());
        initData();
        adapter = new GameSumItemAdapter(gameSummaries);

        handler = new MyHandler();
    }

    private void initData() {
        offset = 0;
        gameSummaries = gameService.getWishGames(offset, limit);
        offset += limit;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, container, false);
        // 设置recycler view
        recyclerView = view.findViewById(R.id.recycle_me);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()); // final 修饰其被赋值后无法被改变
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_me);
            swipeRefreshLayout.setColorSchemeColors(
                    ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                    ContextCompat.getColor(getActivity(), R.color.colorAccent),
                    ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
            );
            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE); // 进度条颜色
            swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);

            // 刷新时候的动作
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            new Thread() {
                                @Override
                                public void run() {
                                    offset = 0;
                                    List<GameSummary> list = null;
                                    list = gameService.getWishGames(offset, limit);
                                    if (list != null) {
                                        Message message = Message.obtain();
                                        message.what = MyHandler.RENEW_DATA;
                                        message.obj = list;

                                        handler.sendMessage(message);
                                    }
                                }
                            }.start();
                            adapter.updateData(gameSummaries);
                            adapter.notifyDataSetChanged();
                            recyclerView.scrollToPosition(0);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }, 1000);
                }
            });
        }

        return view;
    }

    static class GameSumItemAdapter extends RecyclerView.Adapter<GameViewHolder> {
        private List<GameSummary> gameSummaries;


//    private ArrayList<String> mData;
        ImgHandler handler;

        public GameSumItemAdapter(List<GameSummary> data) {
            this.gameSummaries = data;
        }

        public void updateData(List<GameSummary> data) {
            this.gameSummaries = data;
            notifyDataSetChanged();
        }

        @Override
        public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.game_item, parent, false); // 这里取layout
            GameViewHolder viewHolder = new GameViewHolder(v);
            return viewHolder;
        }

        Bitmap bit;
        ImageView imageView;

        @Override
        public void onBindViewHolder(final GameViewHolder holder, int position) {
//        holder.tv.setText(mData.get(position));
            // set the CardView
            holder.gameId = gameSummaries.get(position).getId();
            holder.gameItemNameChineseView.setText(gameSummaries.get(position).getNameCN());
            holder.gameItemNameEnglishView.setText(gameSummaries.get(position).getNameEN());
            holder.gameItemPriceCurrView.setText(gameSummaries.get(position).getPriceCNY());
            holder.gameItemRegionView.setText(gameSummaries.get(position).getRegion());
            holder.gameItemSaleRateView.setText(gameSummaries.get(position).getSaleRate());
            holder.imageUrl = gameSummaries.get(position).getImgUrl();
            imageView = holder.gameItemCover;

            new Thread() {
                @Override
                public void run() {
                    try {
                        //把传过来的路径转成URL
                        URL url = new URL(holder.imageUrl);
                        //获取连接
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        //使用GET方法访问网络
                        connection.setRequestMethod("GET");
                        //超时时间为10秒
                        connection.setConnectTimeout(10000);
                        //获取返回码
                        int code = connection.getResponseCode();
                        if (code == 200) {
                            InputStream inputStream = connection.getInputStream();
                            //使用工厂把网络的输入流生产Bitmap
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            //利用Message把图片发给Handler
                            Message msg = Message.obtain();
                            msg.obj = bitmap;
                            msg.what = FragmentHome.GameSumItemAdapter.ImgHandler.GET_IMAGE_SUC;
                            handler.sendMessage(msg);
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        //网络连接错误
                    }
                }
            }.start();


        }

        @Override
        public int getItemCount() {
            return gameSummaries == null ? 0 : gameSummaries.size();
        }

        class ImgHandler extends Handler {
            final static public int GET_IMAGE_SUC = 4;
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_IMAGE_SUC:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        bit = bitmap;
                        imageView.setImageBitmap(bit);
                        break;
                }
            }
        }
    }









    static class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public int gameId;

        public CardView cardView;
        public String imageUrl;
        public ImageView gameItemCover;
        public TextView gameItemNameChineseView;
        public TextView gameItemNameEnglishView;
        public TextView gameItemPriceCurrView;
        public TextView gameItemPriceOriView;
        public TextView gameItemRegionView;
        public TextView gameItemSaleRateView;

        public GameSummary gameSummary;

        MyHandler handler;


        //        TextView tv;
        public GameViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.game_item);
            gameItemCover = (ImageView) view.findViewById(R.id.game_item_cover);
            gameItemNameChineseView = (TextView) view.findViewById(R.id.game_item_name_chinese);
            gameItemNameEnglishView = (TextView) view.findViewById(R.id.game_item_name_english);
            gameItemPriceCurrView = (TextView) view.findViewById(R.id.game_item_price_curr);
            gameItemPriceOriView = (TextView) view.findViewById(R.id.game_item_price_ori);
            gameItemRegionView = (TextView) view.findViewById(R.id.game_item_region);
            gameItemSaleRateView = (TextView) view.findViewById(R.id.game_item_sale_rate);

            imageUrl = new String("");

//            tv = (TextView) view.findViewById(R.id.test_text); // 这里取id
            view.setOnClickListener(this);

            gameSummary = new GameSummary(
                    1,
                    ""+1,
                    ""+1+1,
                    "",
                    "(日区)",
                    "370",
                    "90%",
                    "",
                    false,
                    false,
                    false
            );

        }

        public void updateGameSummary(GameSummary summary) {

            if (summary == null) return;
            this.gameSummary = summary;

            Context context = itemView.getContext();
            if (context == null) return;

            gameId = summary.getId();
            imageUrl = summary.getImgUrl();
            gameItemNameChineseView.setText(summary.getNameCN());
            gameItemNameEnglishView.setText(summary.getNameEN());
            gameItemPriceCurrView.setText(summary.getPriceCNY());
            gameItemRegionView.setText(summary.getRegion());
            gameItemSaleRateView.setText(summary.getSaleRate());

        }

        @Override
        public void onClick(View view) {
            Log.e("MainActivity", "==>Game onClick...Item");

            if (gameSummary == null) {
                return;
            }
            if (view == null) {
                return;
            }

            Context context = view.getContext();
            if (context == null) return;
            Intent intent = new Intent(context, GameDetail.class);
            intent.putExtra("gameId", gameId);

            if (context instanceof Activity) {
                Activity activity = (Activity) context;

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, gameItemCover, "cover").toBundle();
                ActivityCompat.startActivity(activity, intent, bundle);
            }
        }
    }







    class MyHandler extends Handler {

        final static public int INIT_DATA = 1;
        final static public int RENEW_DATA = 2;
        final static public int UPDATE_DATA = 3;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT_DATA:
                case RENEW_DATA:
                    gameSummaries = (List<GameSummary>) msg.obj;
                    adapter.updateData(gameSummaries);
                    adapter.notifyDataSetChanged();
                    break;
                case UPDATE_DATA:
                    List<GameSummary> list = (List<GameSummary>) msg.obj;
                    for (int i = 0; i < list.size(); i++) {
                        if (gameSummaries == null) {
                            gameSummaries = new ArrayList<>();
                        }
                        gameSummaries.add(list.get(i));
                    }
                    adapter.updateData(gameSummaries);
                    adapter.notifyDataSetChanged();
                    break;

            }
        }
    }
}
