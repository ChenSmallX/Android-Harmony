package top.chensmallx.android_harmony.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import top.chensmallx.android_harmony.FragmentHome;
import top.chensmallx.android_harmony.FragmentMe;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String [] tabTitles = new String[]{"主页", "我的"};

    public MyFragmentPagerAdapter(FragmentManager fm) { super(fm);}

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new FragmentMe(); // this fill up with the fragment_home and me
        }
        return new FragmentHome();
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
