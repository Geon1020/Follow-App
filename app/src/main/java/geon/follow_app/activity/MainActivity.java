package geon.follow_app.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import geon.follow_app.R;
import geon.follow_app.adapter.MainPageAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.tvPageName)
    TextView tvPageName;
    @BindView(R.id.tlLayout)
    TabLayout tlLayout;
    @BindView(R.id.vpPager)
    ViewPager vpPager;

    private MainPageAdapter mainPageAdapter;
    private ArrayList<String> tabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabNames = new ArrayList<>();
        tabNames.add("홈");
        tabNames.add("통계");
        tabNames.add("랭킹");
        tabNames.add("이야기");
        tabNames.add("마이페이지");

        tlLayout.addTab(tlLayout.newTab().setText(tabNames.get(0)));
        tlLayout.addTab(tlLayout.newTab().setText(tabNames.get(1)));
        tlLayout.addTab(tlLayout.newTab().setText(tabNames.get(2)));
        tlLayout.addTab(tlLayout.newTab().setText(tabNames.get(3)));
        tlLayout.addTab(tlLayout.newTab().setText(tabNames.get(4)));

        mainPageAdapter = new MainPageAdapter(getSupportFragmentManager());
        vpPager.setAdapter(mainPageAdapter);
        vpPager.setOffscreenPageLimit(5);
        vpPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlLayout));
        tlLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpPager.setCurrentItem(tab.getPosition());
                tvPageName.setText(tabNames.get(tab.getPosition()));
                mainPageAdapter.getFragment(tab.getPosition()).onResume();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Fragment fragment = mainPageAdapter.getFragment(position);
        if(fragment != null) {
            fragment.onResume();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
