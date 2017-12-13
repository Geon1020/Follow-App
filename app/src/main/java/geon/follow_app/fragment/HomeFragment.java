package geon.follow_app.fragment;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import geon.follow_app.R;
import geon.follow_app.activity.GoodsListActivity;
import geon.follow_app.util.Preference;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SensorEventListener {
    @BindView(R.id.cpCount)
    CircularProgressBar cpCount;
    @BindView(R.id.tvCount)
    TextView tvCount;

    private int walkCount = 0;
    private int alreadyCount = 0;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;
    private int count = 0;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        count = Integer.parseInt(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT));


        return view;
    }

    @OnClick ({R.id.tvPick})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tvPick:
                intent = new Intent(getActivity(), GoodsListActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (accelerormeterSensor != null) {
            sensorManager.registerListener(this, accelerormeterSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = sensorEvent.values[SensorManager.DATA_X];
                y = sensorEvent.values[SensorManager.DATA_Y];
                z = sensorEvent.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    alreadyCount++;
                    if(alreadyCount > 10) {
                        walkCount++;
                        cpCount.setProgress((walkCount / 2));
                        tvCount.setText((walkCount / 2) + "");

                        if(TextUtils.isEmpty(Preference.getStringPreference(Preference.PREFS_KEY.WALK_COUNT))) {
                            Preference.setStringPreference(Preference.PREFS_KEY.WALK_COUNT, String.valueOf((walkCount / 2)));
                        } else {
                            Preference.setStringPreference(Preference.PREFS_KEY.WALK_COUNT, String.valueOf(((walkCount / 2) + count)));

                        }
                    }
                }

                lastX = sensorEvent.values[DATA_X];
                lastY = sensorEvent.values[DATA_Y];
                lastZ = sensorEvent.values[DATA_Z];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
