package com.example.sotiks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.security.Key;

public class ButtonsActivity extends AppCompatActivity {
    String LogTag = "Buttonss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Обработайте отпускание клавиши, верните true, если обработка выполнена
        Log.i(LogTag,String.valueOf(keyCode));
        Log.i(LogTag,String.valueOf(event));

            switch (keyCode) {
                case KeyEvent.KEYCODE_MENU:
                    Toast.makeText(this, "Нажата кнопка Меню", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка Меню");
                    return true;

                case KeyEvent.KEYCODE_SEARCH:
                    Toast.makeText(this, "Нажата кнопка Поиск", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка Поиск");
                    return true;
                case KeyEvent.KEYCODE_CAMERA:
                    Toast.makeText(this, "Нажата кнопка камеры ", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка камеры");
                    return true;
                case KeyEvent.KEYCODE_BACK:
//                    onBackPressed();
                    Toast.makeText(this, "Кнопка назад нажата", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Кнопка назад нажата");
                    return true;
                case KeyEvent.KEYCODE_VOLUME_UP:
                    event.startTracking();
                    Toast.makeText(this, "Нажата кнопка громкости вверх", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка громкости вверх");
                    return true;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    Toast.makeText(this, "Нажата кнопка вниз", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка громкости вниз");
                    return false;
                case KeyEvent.KEYCODE_ASSIST:
                    Toast.makeText(this, "Нажата кнопка питания", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата кнопка питания");
                    return false;
                case KeyEvent.KEYCODE_UNKNOWN:
                    Toast.makeText(this, "Нажата неизвестная кнопка", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(LogTag, "Нажата неизвестная кнопка");
                    Log.i(LogTag, String.valueOf(keyCode));
                    return false;
            }
            return super.onKeyDown(keyCode, event);
    }

    protected void onUserLeaveHint() {
        Log.i(LogTag, "Кнопка Home нажата");
        Toast toast = Toast.makeText(getApplicationContext(), "Нажата кнопка HOME", Toast.LENGTH_SHORT);
        toast.show();
        super.onUserLeaveHint();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      if(keyCode==KeyEvent.KEYCODE_HEADSETHOOK){
          Toast toast = Toast.makeText(getApplicationContext(), "чтото нажато ", Toast.LENGTH_SHORT);
          Log.i(LogTag, "Нажата неизвестная кнопка");
      }
        return super.onKeyDown(keyCode, event);
    }
}
