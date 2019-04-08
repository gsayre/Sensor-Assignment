package com.example.sensorassignment;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Sensor2 extends AppCompatActivity implements SensorEventListener {

    PlotViewAcc pv = null;
    ImageButton imageb2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor2);

        pv = (PlotViewAcc) findViewById(R.id.pv2);
        SensorManager sm;
        Sensor s;

        pv.updateAxes(0);
        pv.updateAxes(1);
        pv.updateAxes(2);
        pv.updateAxes(3);
        pv.updateAxes(4);
        pv.updateAxes(5);
        pv.updateAxes(6);
        pv.updateAxes(7);
        pv.updateAxes(8);
        pv.updateAxes(9);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        sm.registerListener(this, s, 100000);

    }

    void buttonClick (View v) {
        switch (v.getId()) {
            case R.id.back: {
                Intent x = new Intent(this, MainActivity.class);
                startActivity(x);
            }
        }
    }


    int time = 10;
    int sVals = 1;
    float Accel = 0;
    float prevValue = 0;
    float prevprevValue = 0;
    float mean = 0;
    float stdev = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        Accel = (float) Math.sqrt((event.values[0] * event.values[0]) + (event.values[1] * event.values[1]) + (event.values[2] * event.values[2]));
        pv.addPoint(Accel);
        pv.invalidate();

        imageb2 = (ImageButton) findViewById(R.id.ib2);


        if (sVals == 1) {
            pv.addSvals(Accel);
            pv.addStDev(0);
            prevValue = Accel;
        }
        if (sVals == 2) {
            pv.addSvals((prevValue + Accel) / 2);
            mean = (prevValue + Accel) / 2;
            if (prevValue == mean && Accel == mean) {
                pv.addStDev(0);
            } else {
                stdev = (float) Math.sqrt((((prevValue - mean)*(prevValue - mean)) +((Accel - mean) * (Accel - mean))) / 2);
                pv.addStDev(stdev * stdev);
            }
            prevprevValue = prevValue;
            prevValue = Accel;
        }
        if (sVals >= 3) {
            pv.addSvals((prevValue + prevprevValue + Accel) / 3);
            mean = (prevValue + prevprevValue + Accel) / 3;
            if (prevValue == mean && prevprevValue == mean && Accel == mean) {
                pv.addStDev(0);
            } else {
                stdev = (float) Math.sqrt((((prevValue - mean) * (prevValue - mean)) + ((Accel - mean) * (Accel - mean)) + ((prevprevValue - mean) * (prevprevValue - mean))) / 3);
                pv.addStDev(stdev * stdev);
            }
            prevprevValue = prevValue;
            prevValue = Accel;
        }

        Log.v("MYTAG", "Accel = " + Accel);
        Log.v("MYTAG", "St. Dev = " + (float) Math.sqrt(((prevValue - mean) + (Accel - mean) + (prevprevValue - mean)) / 3));

        if (imageb2 != null) {
            if (mean < 10) {
                imageb2.setBackgroundResource(R.drawable.pacrah);
            }else if (mean < 15) {
                imageb2.setBackgroundResource(R.drawable.pa);
            } else {
                imageb2.setBackgroundResource(R.drawable.pafast);
            }
        }

        pv.updateAxes(time);
        sVals++;
        time++;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
