package com.example.sotiks;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class FlashActivity extends AppCompatActivity {
    private FlashClass Flash = new FlashClass(this);
    private boolean StatusCamera;
    public int Count;
    private boolean StatusTest = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

    }

    public void startLantern(View view) {
        Count=(int) (Math.random() * 4)+1;

        FlashClass flashClass = new FlashClass(this);
        ImageView Light = findViewById(R.id.light);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (StatusCamera==false){
                flashClass.flashOn();
                Light.setVisibility(View.VISIBLE);
                StatusCamera = true;
            }
            else{
                flashClass.flashOff();
                StatusCamera=false;
                Light.setVisibility(View.INVISIBLE);
            }

        }

        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                for (int i=0;i<=Count;i++){

                    try {
                        flashClass.flashOn();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Light.setVisibility(View.VISIBLE);


                            }
                        });
                        Thread.sleep(2000);// Метод sleep создает задержку
                        flashClass.flashOff();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Light.setVisibility(View.INVISIBLE);


                            }
                        });

                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent CheckLantern = new Intent(FlashActivity.this,CheckTestLanternActivity.class);
                CheckLantern.putExtra("COUNT",String.valueOf(Count));
                startActivity(CheckLantern);
            }
        }).start();


        }


}