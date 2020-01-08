package top.chensmallx.android_harmony;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import top.chensmallx.android_harmony.adapter.EndlessRecyclerViewScrollListener;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.GameService;

public class SearchPage extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private GameService service;

    SearchView searchView;
    RecyclerView recyclerView;

    List<GameSummary> testGame;

    public GameSumItemAdapter adapter;
    MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page_activity);

        getSupportActionBar().hide();

        searchView = (SearchView) findViewById(R.id.search_page_search_bar);

        testGame = new ArrayList<>();

        for (int i = 0; i <= 1; i ++) {
            GameSummary summary = new GameSummary(
                    i,
                    ""+i,
                    ""+i+i,
                    "",
                    "",
                    "",
                    "",
                    "",
                    false,
                    false,
                    false
            );
//            gameService.addToWishList(summary);
            testGame.add(summary);
        }

//        gameSummaries = gameService.getWishGames(offset, limit);
//        offset += limit;

        service = new GameService(getApplicationContext());
        adapter = new GameSumItemAdapter(testGame);
        handler = new MyHandler();

        recyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        Log.e("onQueryTextSubmit", query);
        if (searchView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                // 这将让键盘在所有的情况下都被隐藏，但是一般我们在点击搜索按钮后，输入法都会乖乖的自动隐藏的。
                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0); // 输入法如果是显示状态，那么就隐藏输入法
            }
            searchView.clearFocus(); // 不获取焦点
        }
        new Thread() {
            @Override
            public void run() {
                List<GameSummary> list = null;
                try {
                    list = service.searchGameByName(query, 0, 100);
                } catch (IOException e) {
                    Log.e("IOE", e.toString());
                }
                if (list != null) {
                    Message message = Message.obtain();
                    message.what = MyHandler.UPDATE_DATA;
                    message.obj = list;

                    handler.sendMessage(message);
                }
            }
        }.start();

        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
//        String selection = ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY + " LIKE '%" + newText + "%' " + " OR "
//                + ContactsContract.RawContacts.SORT_KEY_PRIMARY + " LIKE '%" + newText + "%' ";
        // String[] selectionArg = { queryText };
//        mCursor = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, PROJECTION, selection, null, null);
//        mAdapter.swapCursor(mCursor); // 交换指针，展示新的数据
        Log.e("onQueryTextChange", newText);
        new Thread() {
            @Override
            public void run() {
                List<GameSummary> list = null;
                try {
                    list = service.searchGameByName(newText, 0, 100);
                } catch (IOException e) {
                    Log.e("IOE", e.toString());
                }
                if (list != null) {
                    Message message = Message.obtain();
                    message.what = MyHandler.UPDATE_DATA;
                    message.obj = list;

                    handler.sendMessage(message);
                }
            }
        }.start();


        return true;
    }


    static class GameSumItemAdapter extends RecyclerView.Adapter<GameViewHolder> {
        private List<GameSummary> gameSummaries;

        //    private ArrayList<String> mData;
        ImgHandler handler;

        public GameSumItemAdapter(List<GameSummary> data) {
            handler = new ImgHandler();
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
            holder.gameItemPriceCurrView.setText(gameSummaries.get(position).getPriceCNY()+"CNY");
            holder.gameItemRegionView.setText(gameSummaries.get(position).getRegion());
            holder.gameItemSaleRateView.setText(gameSummaries.get(position).getSaleRate()+"%");
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
                            msg.what = ImgHandler.GET_IMAGE_SUC;
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



    static class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
            gameItemPriceCurrView.setText(summary.getPriceCNY()+" CNY");
            gameItemRegionView.setText(summary.getRegion());
            gameItemSaleRateView.setText(summary.getSaleRate()+"%");

        }

        @Override
        public void onClick(View view) {
            Log.e("searchPage", "==>Game onClick...Item");

            if (gameSummary == null) {
                return;
            }
            if (itemView == null) {
                return;
            }

            Context context = itemView.getContext();
            if (context == null) {
                return;
            }
            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("gameId", gameId);
            intent.putExtra("gameName", gameSummary.getNameCN());


            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, gameItemCover, "gameId").toBundle();
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
                case UPDATE_DATA:
                    testGame = (List<GameSummary>) msg.obj;
                    adapter.updateData(testGame);
                    adapter.notifyDataSetChanged();
                    break;

            }
        }
    }

}
