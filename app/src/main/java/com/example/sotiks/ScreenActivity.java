package com.example.sotiks;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ScreenActivity extends AppCompatActivity {
    private ConstraintLayout BackgroundTestScreen;
    private Button ButtonTestScreen;
    private TextView TextGreeting ;
    private TextView TextWarning;
    private int Counter;
    private ImageView GI;
    private boolean Status = false;
    private boolean StatusTest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        BackgroundTestScreen = findViewById(R.id.SreenTestBackground);
        ButtonTestScreen = findViewById(R.id.TestScreen);

        TextGreeting = findViewById(R.id.GreetingScreenTestBackgraund);
        TextWarning = findViewById(R.id.WarningTestScreen);
        GI = findViewById(R.id.ImgGreeetingTestScreen);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void testScreen(View view) {

        Status= !Status;
        if (Status ) {
            ButtonTestScreen.setVisibility(View.INVISIBLE);
            ButtonTestScreen.setText("Закончить Тест");
            TextGreeting.setVisibility(View.GONE);
            TextWarning.setVisibility(View.GONE);
            GI.setVisibility(View.GONE);

            new Thread(new Runnable() {

                @Override
                public void run() {

                    while (Status) {
                        try {
                            Counter++;
                            switch (Counter) {
                                case (1):
                                    BackgroundTestScreen.setBackgroundColor(getColor(R.color.Red));

                                    break;
                                case (2):
                                    BackgroundTestScreen.setBackgroundColor(getColor(R.color.Blue));
                                    break;
                                case (3):
                                    BackgroundTestScreen.setBackgroundColor(getColor(R.color.Green));
                                    break;
                                case (4):
                                    BackgroundTestScreen.setBackgroundColor(getColor(R.color.white));
                                    break;
                                case (5):

                                    BackgroundTestScreen.setBackgroundColor(getColor(R.color.black));


                                    Counter = 0;

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ButtonTestScreen.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    break;
                            }
                            Thread.sleep(4000);// Метод sleep создает задержку

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        else {
            Counter = 0;
            ButtonTestScreen.setText("Начать Тест");
            TextGreeting.setVisibility(View.VISIBLE);
            TextWarning.setVisibility(View.VISIBLE);
            GI.setVisibility(View.VISIBLE);
            BackgroundTestScreen.setBackgroundColor(getColor(R.color.black));
            setContentView(R.layout.check_test_screen);
        }
    }

    public void testScreenSuccessfully(View view) {

        Intent Main = new Intent(ScreenActivity.this, MainActivity.class);
        startActivity(Main);
    }

    public void testScreenFail(View view) {
        Intent Screen = new Intent(ScreenActivity.this, ScreenActivity.class);
        startActivity(Screen);
    }
}