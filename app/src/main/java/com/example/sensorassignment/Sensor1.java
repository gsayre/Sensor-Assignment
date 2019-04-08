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
import android.widget.ImageView;

public class Sensor1 extends AppCompatActivity implements SensorEventListener {


    PlotViewLight pv = null;
    ImageButton imageb1 = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor1);

        pv = (PlotViewLight) findViewById(R.id.pv1);
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
        s = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

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
    float prevValue = 0;
    float prevprevValue = 0;
    float mean = 0;
    float stdev = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        ImageView imageb1 = (ImageView) findViewById(R.id.ib1);
        pv.addPoint(event.values[0]);
        pv.invalidate();


        if (sVals == 1) {
            pv.addSvals(event.values[0]);
            pv.addStDev(0);
            prevValue = event.values[0];
        }
        if (sVals == 2) {
            pv.addSvals((prevValue + event.values[0]) / 2);
            mean = (prevValue + event.values[0]) / 2;
            if (prevValue == mean && event.values[0] == mean) {
                pv.addStDev(0);
            } else {
                stdev = (float) Math.sqrt((((prevValue - mean)*(prevValue - mean)) +((event.values[0] - mean) * (event.values[0] - mean))) / 2);
                pv.addStDev(stdev * stdev);
            }
            prevprevValue = prevValue;
            prevValue = event.values[0];
        }
        if (sVals >= 3) {
            pv.addSvals((prevValue + prevprevValue + event.values[0]) / 3);
            mean = (prevValue + prevprevValue + event.values[0]) / 3;
            if (prevValue == mean && prevprevValue == mean && event.values[0] == mean) {
                pv.addStDev(0);
            } else {
                stdev = (float) Math.sqrt((((prevValue - mean) * (prevValue - mean)) + ((event.values[0] - mean) * (event.values[0] - mean)) + ((prevprevValue - mean) * (prevprevValue - mean))) / 3);
                pv.addStDev(stdev * stdev);
            }
            prevprevValue = prevValue;
            prevValue = event.values[0];
        }


        //Log.v("MYTAG", "Light = " + event.values[0]);
        //Log.v("MYTAG", "StDev = " + (float) Math.sqrt(((prevValue - mean) + (event.values[0] - mean) + (prevprevValue - mean)) / 3));

        if (imageb1 != null) {
            if (mean < 100) {
                imageb1.setBackgroundResource(R.drawable.lbunlit);
            }else if (mean < 400) {
                imageb1.setBackgroundResource(R.drawable.lblit);
            } else {
                imageb1.setBackgroundResource(R.drawable.lbsupalit);
            }
        }

        pv.updateAxes(time);
        sVals++;
        time++;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
