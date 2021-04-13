package com.example.sotiks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AkcelerometrActivity extends AppCompatActivity {
    private SensorManager sm;
    private Sensor s;
    private SensorEventListener sv;
    public ImageView imageAkcelerometr1;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akcelerometr);

        tv = findViewById(R.id.resultAkcelerometr);
        imageAkcelerometr1 = findViewById(R.id.akcelerometrTest);
       sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sm !=null ) s = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sv = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, event.values);

                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(
                        rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);


                float[] orientations = new float[3];
                SensorManager.getOrientation(
                  remappedRotationMatrix,
                  orientations
                );
                for (int i =0; i<3;i++){
                    orientations[i]=(float)(Math.toDegrees(orientations[i]));
                }
            tv.setText(String.valueOf((int) orientations[2]) );
                imageAkcelerometr1.setRotation(-orientations[2]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

    }
    @Override
    public void onResume() {

        super.onResume();
     sm.registerListener(sv,s,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {


        super.onPause();
        sm.unregisterListener(sv);
    }
}