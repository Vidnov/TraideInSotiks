package com.example.sotiks;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
//import android.bluetooth.*;

public class BluetoothsActivity extends AppCompatActivity {

    private String TagLOG = "Bluethoo";

    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooths);
        BluetoothAdapter bluetooth= BluetoothAdapter.getDefaultAdapter();
        if(bluetooth!=null)
        {

            Toast toast = Toast.makeText(getApplicationContext(), "Зуб поддерживается ", Toast.LENGTH_SHORT);
            Log.i(TagLOG, "Зуб поддерживается ");
            if(bluetooth.isEnabled()){
                Log.i(TagLOG, "Зуб включен ");
                String mydeviceaddress= bluetooth.getAddress();
                String mydevicename= bluetooth.getName();
                int mydevicestate= bluetooth.getState();
                status = mydevicename + " : " + mydeviceaddress + ":"+ mydevicestate;
                Log.i(TagLOG, status);
                Toast.makeText(this, status, Toast.LENGTH_LONG).show();
            }else
            {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.i(TagLOG, "Зуб выключен ");

            }
// С Bluetooth все в порядке.
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Зуб не поддерживается ", Toast.LENGTH_SHORT);
            Log.i(TagLOG, "Зуб поддерживается ");
        }


    }
}