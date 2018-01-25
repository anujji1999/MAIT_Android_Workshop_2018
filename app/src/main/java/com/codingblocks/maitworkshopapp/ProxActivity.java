package com.codingblocks.maitworkshopapp;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class ProxActivity extends AppCompatActivity {
    public static final String TAG = "SENS";

    ConstraintLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox);
        backgroundLayout = findViewById(R.id.background);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            Log.d(TAG, sensor.getName());
            Log.d(TAG, sensor.getVendor());
            Log.d(TAG, sensor.getStringType());
            Log.d(TAG, "=====================");
        }
        Sensor proxSensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener sel  = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.d(TAG, "Proximity = " + event.values[0]);
                if (event.values[0] == 0F) {
                    backgroundLayout.setBackgroundColor(Color.BLACK);
                } else {
                    backgroundLayout.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        sm.registerListener(
                sel,
                proxSensor,
                1000 * 1000
        );
    }
}
