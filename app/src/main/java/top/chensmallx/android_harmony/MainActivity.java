package top.chensmallx.android_harmony;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

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

        initViews();

    }

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
        home_tab.setIcon(R.mipmap.ic_launcher_round);
        me_tab.setIcon(R.mipmap.ic_launcher_round);

    }
}
