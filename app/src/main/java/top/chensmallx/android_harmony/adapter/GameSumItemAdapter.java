package top.chensmallx.android_harmony.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import top.chensmallx.android_harmony.R;
import top.chensmallx.android_harmony.model.GameSummary;

public class GameSumItemAdapter extends RecyclerView.Adapter<GameSumItemAdapter.ViewHolder> {

    private List<GameSummary> gameSummaries;

//    private ArrayList<String> mData;

    public GameSumItemAdapter(List<GameSummary> data) {
        this.gameSummaries = data;
    }

    public void updateData(List<GameSummary> data) {
        this.gameSummaries = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.game_item, parent, false); // 这里取layout
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tv.setText(mData.get(position));
        // set the CardView
        holder.gameId = gameSummaries.get(position).getId();
        holder.gameItemNameChineseView.setText(gameSummaries.get(position).getNameCN());
        holder.gameItemNameEnglishView.setText(gameSummaries.get(position).getNameEN());
        holder.gameItemPriceCurrView.setText(gameSummaries.get(position).getPriceCNY());
        holder.gameItemRegionView.setText(gameSummaries.get(position).getRegion());
        holder.gameItemSaleRateView.setText(gameSummaries.get(position).getSaleRate());
        holder.imageUrl = gameSummaries.get(position).getImgUrl();
    }

    @Override
    public int getItemCount() {
        return gameSummaries == null ? 0 : gameSummaries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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

//        TextView tv;
        public ViewHolder(View view) {
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

        }
    }
}
