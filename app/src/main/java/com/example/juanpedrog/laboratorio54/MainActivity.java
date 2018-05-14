package com.example.juanpedrog.laboratorio54;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView steps;
    Button restart;
    int step;

    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        step=0;
        steps = findViewById(R.id.textView_steps);
        restart = findViewById(R.id.button_restart);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT);
        }

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step=0;
                steps.setText(step+"");
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        steps.setText(""+(step++));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
