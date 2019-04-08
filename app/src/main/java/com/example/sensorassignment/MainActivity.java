package com.example.sensorassignment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView textView1 = null;
    TextView textView2 = null;
    TextView textView3 = null;
    TextView textView4 = null;
    TextView textView5 = null;
    TextView textView6 = null;
    TextView textView7 = null;
    TextView textView8 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);
        textView5 = findViewById(R.id.tv5);
        textView6 = findViewById(R.id.tv6);
        textView7 = findViewById(R.id.tv7);
        textView8 = findViewById(R.id.tv8);

        SensorManager sm;
        Sensor s1;
        Sensor s2;

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        s1 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        s2 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        textView1.setText("Status: Accelerometer Sensor is present");
        textView2.setText("Range: " + s1.getMaximumRange());
        textView3.setText("Resolution: " + s1.getResolution());
        textView4.setText("Delay: " + s1.getMinDelay());

        textView5.setText("Status: Light Sensor is present");
        textView6.setText("Range: " + s2.getMaximumRange());
        textView7.setText("Resolution: " + s2.getResolution());
        textView8.setText("Delay: " + s2.getMinDelay());





    }

    void buttonClick (View v) {
        switch (v.getId()) {
            case R.id.s1: {
                Intent y = new Intent(this, Sensor1.class);
                startActivity(y);
                break;
            }
            case R.id.s2: {
                Intent z = new Intent(this, Sensor2.class);
                startActivity(z);
                break;
            }
        }
    }
}
