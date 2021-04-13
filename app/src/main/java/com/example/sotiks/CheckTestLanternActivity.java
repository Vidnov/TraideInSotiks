package com.example.sotiks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckTestLanternActivity extends AppCompatActivity {

    private EditText  CheckVibraite;
    private  String msg;
    private TextView Test;
    private int Count;
    private int Count2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_test_lantern);
        CheckVibraite   = (EditText)findViewById(R.id.editTextNumberCheckLantern);                 // Input
        Test = (TextView)findViewById(R.id.TestCheckLantern);                                         // Текст для теста

        Intent intent = getIntent();
        String CountLantern =intent.getStringExtra("COUNT");
        Count = Integer.parseInt (CountLantern)+1;
        Count2=Integer.parseInt (CountLantern);
    }



    public void checkTestLantern(View view) {
        Log.i("tests", String.valueOf(CheckVibraite));
        msg = String.valueOf(CheckVibraite.getText().toString());
        Count2=Integer.parseInt(msg);

        if(Count==Count2){
            Test.setText("Тест пройден");
            ImageView check = findViewById(R.id.CheckCompliteLantern);
            check.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);// Метод sleep создает задержку
                        check.setVisibility(View.INVISIBLE);
                        Intent Menu = new Intent(CheckTestLanternActivity.this,MainActivity.class);
                        startActivity(Menu);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

        }else {
            Test.setText("Тест не пройден");
            setContentView(R.layout.retry_test);
            Button retry = findViewById(R.id.testVibratonRetry);
            Button menu = findViewById(R.id.gotoMenu);
            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TestVibration = new Intent(CheckTestLanternActivity.this, FlashActivity.class);
                    startActivity(TestVibration);
                }
            });
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Menu = new Intent(CheckTestLanternActivity.this,MainActivity.class);
                    startActivity(Menu);
                }
            });


        }
    }
}