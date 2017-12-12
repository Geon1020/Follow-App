package geon.follow_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import geon.follow_app.fragment.*;

public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new StatisticsFragment();
            case 2:
                return new RankingFragment();
            case 3:
                return new StoryFragment();
            case 4:
                return new MyPageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
