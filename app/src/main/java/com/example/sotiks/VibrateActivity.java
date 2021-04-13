package com.example.sotiks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VibrateActivity extends AppCompatActivity {
    private int Counter = 0;
   private  boolean Status=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrate);
    }

    public void startTestVibration(View view) {

        //Создаем новое намерение для запуска сервиса использования вибрации
        Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
        startService(intentVibrate);



    }
}