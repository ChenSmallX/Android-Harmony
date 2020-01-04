package top.chensmallx.android_harmony.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import top.chensmallx.android_harmony.model.GameSummary;

public class GameItemHolder extends RecyclerView.ViewHolder {
    private GameSummary gameSummary;

    public GameItemHolder(@NonNull View itemView) {
        super(itemView);
    }
}
