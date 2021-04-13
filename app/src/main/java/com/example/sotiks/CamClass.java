package com.example.sotiks;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class CamClass extends Activity {
    Button btnCamStart;
    ImageView imageView;
    Context context;
    public void CamClass(Context context){
        this.context=context;
    }

    public void startCam(){
        //btnCamStart=findViewById(R.id.camStart);
        imageView = findViewById(R.id.camImg);
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CamClass.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        btnCamStart.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }
}
