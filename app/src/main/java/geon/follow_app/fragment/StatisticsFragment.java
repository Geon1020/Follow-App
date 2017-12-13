package geon.follow_app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import geon.follow_app.R;
import geon.follow_app.util.Preference;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {
    @BindView(R.id.tvDay)
    TextView tvDay;
    @BindView(R.id.tvWeek)
    TextView tvWeek;
    @BindView(R.id.tvMonth)
    TextView tvMonth;
    @BindView(R.id.vPre)
    View vPre;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.vNext)
    View vNext;
    @BindView(R.id.tvWalkCount)
    TextView tvWalkCount;
    @BindView(R.id.tvKcal)
    TextView tvKcal;
    @BindView(R.id.tvKm)
    TextView tvKm;
    @BindView(R.id.tvMin)
    TextView tvMin;
    @BindView(R.id.tvSpeed)
    TextView tvSpeed;

    private Calendar calendar;
    private DateFormat dateFormat;
    private Date date;
    private String curDate;
    private String today;
    private int panCount = 0;

    public StatisticsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        panCount = Integer.parseInt(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT));
        tvDay.setSelected(true);
        setDate();
        calcStat("none");
    }

    @OnClick({R.id.tvDay, R.id.tvWeek, R.id.tvMonth, R.id.vPre, R.id.vNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDay:
                tvDay.setSelected(true);
                tvWeek.setSelected(false);
                tvMonth.setSelected(false);

                vPre.setClickable(true);
                vNext.setClickable(true);

                setDate();
                break;
            case R.id.tvWeek:
                tvDay.setSelected(false);
                tvWeek.setSelected(true);
                tvMonth.setSelected(false);

                vPre.setClickable(false);
                vNext.setClickable(false);
                calcDay("week");

                tvDate.setText(resultDate(curDate) + " ~ " + resultDate(today));
                tvKcal.setText("808 kcal");
                tvMin.setText("283 분");
                tvSpeed.setText("3 km/h");
                tvKm.setText("18190 m");
                tvWalkCount.setText("24241");
                break;
            case R.id.tvMonth:
                tvDay.setSelected(false);
                tvWeek.setSelected(false);
                tvMonth.setSelected(true);

                vPre.setClickable(false);
                vNext.setClickable(false);

                tvDate.setText("12월");
                tvKcal.setText("1305 kcal");
                tvMin.setText("458 분");
                tvSpeed.setText("3 km/h");
                tvKm.setText("30550 m");
                tvWalkCount.setText("39173");
                break;
            case R.id.vPre:
                calcDay("pre");

                if(today.equals(curDate)) {
                    calcStat("none");
                } else {
                    calcStat("pre");
                }
                break;
            case R.id.vNext:
                calcDay("next");

                if(today.equals(curDate)) {
                    calcStat("none");
                } else {
                    calcStat("next");
                }
                break;
        }
    }

    private String resultDate(String date) {
        String[] curDates = date.split("-");

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
        today = dateFormat.format(calendar.getTime());

        tvDate.setText(resultDate(curDate) + " " + setDow());
    }

    private void calcDay(String type) {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar = Calendar.getInstance();

        try {
            date = dateFormat.parse(curDate);

            if (type.equals("pre")) {
                calendar.setTime(date);
                calendar.add(Calendar.DATE, -1);
            } else if (type.equals("next")) {
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
            } else if(type.equals("week")){
                calendar.setTime(date);
                calendar.add(Calendar.DATE, -7);
            }

            curDate = dateFormat.format(calendar.getTime());
            tvDate.setText(resultDate(curDate) + " " + setDow());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void calcStat(String type) {
        int count = 0;
        int cal = 0;
        int min = 0;
        int speed = 0;
        int m = 0;

        if(!TextUtils.isEmpty(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT))) {
            count = Integer.parseInt(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT));

            if(type.equals("none")) {
                count = Integer.parseInt(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT));

                cal = (int) (count * 0.03);
                min = (int) ((count * 1.43) / 60);
                speed = (int) ((count * 2.65) / 1000);
                m = (min * speed);

                tvWalkCount.setText(count + "");
            } else if(type.equals("pre")) {
                if(panCount > 100) {
                    panCount -= 37;
                } else if(panCount < 100) {
                    panCount -= 17;

                    if(panCount < 0){
                        panCount = 0;
                    }
                }

                cal = (int) (panCount * 0.03);
                min = (int) ((panCount * 1.43) / 60);
                speed = (int) ((panCount * 2.65) / 1000);
                m = (min * speed);

                tvWalkCount.setText(panCount + "");
            } else if(type.equals("next")) {
                if(panCount > 100) {
                    panCount += 57;
                } else {
                    panCount += 27;
                }

                cal = (int) (panCount * 0.03);
                min = (int) ((panCount * 1.43) / 60);
                speed = (int) ((panCount * 2.65) / 1000);
                m = (min * speed);
                tvWalkCount.setText(panCount + "");
            }

            tvKcal.setText(cal + " kcal");
            tvMin.setText(min + " 분");
            tvSpeed.setText(speed + " km/h");
            tvKm.setText(m + " m");
        } else {
            tvKcal.setText(cal + " kcal");
            tvMin.setText(min + " 분");
            tvSpeed.setText(speed + " km/h");
            tvKm.setText(m + " m");
            tvWalkCount.setText("0");
        }
    }
}
