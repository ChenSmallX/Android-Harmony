package top.chensmallx.android_harmony;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import top.chensmallx.android_harmony.adapter.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    // several tab elements
    private TabLayout.Tab home_tab;
    private TabLayout.Tab me_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        statusBarTransparent();
//        int height = 0;
//        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            height = getApplicationContext().getResources().getDimensionPixelSize(resourceId);
//        }
//        Toolbar bar = (Toolbar) findViewById(R.id.status_bar_fill);
//        bar.setMinimumHeight(height);

        initViews();

    }

    // bind the tab, page and something
    private void initViews() {

        // bind viewPager with fragment manager
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myFragmentPagerAdapter);

        // bind tabLayout with viewPager
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        home_tab = tabLayout.getTabAt(0);
        me_tab = tabLayout.getTabAt(1);

        // set the tab bar items' text
        home_tab.setText(R.string.tab_home_text);
        me_tab.setText(R.string.tab_me_text);

        // set the tab items' icon
//        home_tab.setIcon(R.mipmap.ic_launcher_round);
//        me_tab.setIcon(R.mipmap.ic_launcher_round);

    }

}
