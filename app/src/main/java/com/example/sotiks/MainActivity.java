package com.example.sotiks;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private TextView resultVoice;
    public ListView DeviceMenu;
    public String[] Lists;
    public ArrayAdapter<String> adapter;
    private Boolean StatusCamera;
    public static final String TAG = "MyApp";
    private FlashClass Flash = new FlashClass(this);
    ImageView camImage;
    ImageView frontCamImage;
    private VoiceClass player = new VoiceClass(this);

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      ////////////////////////////////////////////////////////////////////////////////////////////// Прописываем разрешения
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ||
                (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        )
        {
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }


        StatusCamera=false;

        player.createPlayer();


    }



    public void onBack(View view) {
                                                                                                    //Обработка события нажатия на кнопку назад.
        loadMenu();
        player.stopPlayer();                                                                        // Выключить звуковое сопровождение

    }
    public void Start(View view) {

        loadMenu();
    }



  public void loadMenu(){                                                                           //Отображение списка устройств для тестирования.
      setContentView(R.layout.menu_device);
      Lists = getResources().getStringArray(R.array.menu_device);
      DeviceMenu = (ListView) findViewById(R.id.ListDevice);
      adapter  = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Lists);
      DeviceMenu.setAdapter(adapter);

      DeviceMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              switch(position) {
                  case 0:
                      Flash.flashOff();                                                             // При переходе на страницу фонарика , фонарик выключается в любом случае.
                      Intent Flash = new Intent(MainActivity.this, FlashActivity.class);
                      startActivity(Flash);
                      break;
                  case 1:
                      setContentView(R.layout.audio);
                      resultVoice=findViewById(R.id.resultVoice);
                      break;
                  case 2:
                      setContentView(R.layout.camera);
                      camImage = findViewById(R.id.camImg);
                      frontCamImage = findViewById(R.id.frontCamImg);
                      break;
                  case 3:

                      Intent Akcelerometr = new Intent(MainActivity.this, AkcelerometrActivity.class);
                      startActivity(Akcelerometr);
                      break;
                  case 4:

                      Intent Screen = new Intent(MainActivity.this, ScreenActivity.class);
                      startActivity(Screen);
                      break;
                  case 5:

                      Intent Vibraite = new Intent(MainActivity.this, VibrateActivity.class);
                      startActivity(Vibraite);
                      break;
                  case 6:

                      Intent Buttons = new Intent(MainActivity.this, ButtonsActivity.class);
                      startActivity(Buttons);
                      break;
                  case 7:

                      Intent Bluetooth = new Intent(MainActivity.this, BluetoothsActivity.class);
                      startActivity(Bluetooth);
                      break;
                  case 8:

                      Intent Account = new Intent(MainActivity.this, AccountActivity.class);
                      startActivity(Account);
                      break;
              }

          }
      });
  }
    public void StartSpeachVoice(View view) throws IOException {                                    // Начать запись голоса

        VoiceClass voice = new VoiceClass(this);
        startActivityForResult(voice.onClickSpeach(),10);                               // Записываем голос

    }

    public void startCamera(View view) throws IOException {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.media.action.IMAGE_CAPTURE", 1);

        startActivityForResult(intent,100);                                             // Запускаем камеру

    }

    public void startFrontCamera(View view){                                                        //Запускаем фронтальную камеру
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {       // Получаем ответ от сервера гугл
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK && data!=null &&  requestCode==10){
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);  // Считываем ответ из data
            resultVoice.setText(text.get(0));

        }else if(requestCode==100){

            Log.i("MyApp1", String.valueOf(requestCode));
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            camImage.setImageBitmap(captureImage);
        }else if(requestCode==101){

            Log.i("MyApp1", String.valueOf(requestCode));
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            frontCamImage.setImageBitmap(captureImage);
        }

    }


    public void StartTestDynamic(View view){                                                        //Начинаем  тест динамика
        player.startPlayer();
    }

    @Override
    public void onDestroy() {                                                                       //Обработка события "закрытие приложения"
        super.onDestroy();
        player.stopPlayer();
    }



}