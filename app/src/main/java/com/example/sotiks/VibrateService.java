package com.example.sotiks;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

public class VibrateService extends Service {
    public int Count;


    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        Toast.makeText(getApplicationContext(), "Вибрация активирована", Toast.LENGTH_LONG).show();

        //Указываем длительность вибрации в миллисекундах,
        //в нашем примере будет вибро-сигнал длительностью в 2 секунды
        Count=(int) (Math.random() * 4)+1;



        new Thread(new Runnable() {

                @Override
                public void run() {

                        for (int i=0;i<=Count;i++){

                            try {
                                vibrator.vibrate(2000);
                                Thread.sleep(3000);// Метод sleep создает задержку
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(i==Count){


                            }
                        }
                }
            }).start();

        Intent CheckVibraite = new Intent(VibrateService.this, CheckTestVibrateActivity.class);
        CheckVibraite.putExtra("COUNT",String.valueOf(this.Count));
        Log.i("COUNT", String.valueOf(this.Count));
        CheckVibraite.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(CheckVibraite);


    }



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}