package geon.follow_app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import geon.follow_app.R;
import geon.follow_app.adapter.RankingAdapter;
import geon.follow_app.util.Preference;
import geon.follow_app.vo.RankingItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
    @BindView(R.id.tvToday)
    TextView tvToday;
    @BindView(R.id.rvRanking)
    RecyclerView rvRanking;
    @BindView(R.id.tvMyCount)
    TextView tvMyCount;

    private RankingAdapter rankingAdapter;
    private ArrayList<RankingItem> rankingItems;

    private Calendar calendar;
    private DateFormat dateFormat;
    private Date date;
    private String curDate;

    public RankingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(TextUtils.isEmpty(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT))) {
            tvMyCount.setText("0 걸음");
        } else {
            tvMyCount.setText(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT ) + " 걸음");
        }

        rankingItems = new ArrayList<>();
        setData();
        setDate();

        rankingAdapter = new RankingAdapter(getActivity(), rankingItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvRanking.setLayoutManager(linearLayoutManager);
        rvRanking.setAdapter(rankingAdapter);
    }

    private void setData() {
        rankingItems.clear();

        for (int i = 100; i > 0; i--) {
            RankingItem rankingItem = new RankingItem();

            char one = (char) ((Math.random() * 26) + 97);
            char two = (char) ((Math.random() * 26) + 97);
            char three = (char) ((Math.random() * 26) + 97);
            char four = (char) ((Math.random() * 26) + 97);
            char five = (char) ((Math.random() * 26) + 97);

            rankingItem.setName(String.valueOf(one) + String.valueOf(two) + String.valueOf(three) + String.valueOf(four) + String.valueOf(five));
            rankingItem.setRank(i);

            rankingItems.add(rankingItem);
        }
    }

    private String resultDate() {
        String[] curDates = curDate.split("-");

        return curDates[1] + "월 " + curDates[2] + "일";
    }

    private String setDow() {
        String dow = "";
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();
        try {
            date = dateFormat.parse(curDate);
            calendar.setTime(date);
            int dayNum = calendar.get(Calendar.DAY_OF_WEEK);

            switch (dayNum) {
                case 1:
                    dow = "일요일";
                    break;
                case 2:
                    dow = "월요일";
                    break;
                case 3:
                    dow = "화요일";
                    break;
                case 4:
                    dow = "수요일";
                    break;
                case 5:
                    dow = "목요일";
                    break;
                case 6:
                    dow = "금요일";
                    break;
                case 7:
                    dow = "토요일";
                    break;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dow;
    }

    private void setDate() {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        curDate = dateFormat.format(calendar.getTime());

        tvToday.setText(resultDate() + " " + setDow());
    }
}
