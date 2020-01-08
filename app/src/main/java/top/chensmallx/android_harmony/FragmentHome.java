package top.chensmallx.android_harmony;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import top.chensmallx.android_harmony.adapter.GameSumItemAdapter;
import top.chensmallx.android_harmony.model.GameSummary;
import top.chensmallx.android_harmony.service.GameService;

public class FragmentHome extends Fragment {

    private GameService service;

    private int offset = 0;
    private int limit = 20;

    private RecyclerView recyclerView;
    GameSumItemAdapter adapter;

    @Nullable
    private List<GameSummary> testGame;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = new GameService(getActivity().getApplicationContext());
        testGame = initData();
        adapter = new GameSumItemAdapter(testGame);
    }

    @Nullable
    private List<GameSummary> initData() {
        List<GameSummary> data = null;
        try {
            data = service.getGameList(offset, limit);
            offset += limit;
        } catch (IOException e) {
            System.out.println("出现IO错误："+e.toString());
        }

        if (data != null) {
            return data;
        }

        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 设置recycler view
        recyclerView = view.findViewById(R.id.recycle_home);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()); // final 修饰其被赋值后无法被改变
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_home);
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
//                            ArrayList<String> list = new ArrayList<>();
//                            for (int i = 'z'; i > 'A'; i--) {
//                                list.add(""+(char)i);
//                            }
                            adapter.updateData(initData());
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


}
