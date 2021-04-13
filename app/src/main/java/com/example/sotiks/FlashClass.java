package com.example.sotiks;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraDevice.StateCallback;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.util.Size;

import androidx.annotation.RequiresApi;

import static androidx.core.content.ContextCompat.getSystemService;

public class FlashClass {

    private CameraDevice mCameraDevice = null;
    private boolean flash_status =false;
    private  Context context;
    private Camera.Parameters parameters;
    public static final String LOG_TAG = "FlashClass";
    String[] myCameras = null;
    private CameraManager mCameraManager    = null;
    public String frontCam;
    public String backCam;

    public FlashClass(Context context) {

        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void flashOn(){
        CameraManager cameraManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {          //возможно придется удалить проверку
            cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        }
        try {
            assert cameraManager !=null;
            String cameraId = cameraManager.getCameraIdList()[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId,true);                                  //Включаем фонарик


            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void flashOff(){
        CameraManager cameraManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        }
        try {
            assert cameraManager !=null;
            String cameraId = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                cameraId = cameraManager.getCameraIdList()[0];
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId,false);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    public boolean isFlash_status(){
        return flash_status;
    }





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startCam(){
        mCameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
        try{
                                                                                                    // Получение списка камер с устройства

            myCameras = new String[mCameraManager.getCameraIdList().length];

                                                                                                    // выводим информацию по камере
            for (String cameraID : mCameraManager.getCameraIdList()) {
                Log.i(LOG_TAG, "cameraID: "+cameraID);

                                                                                                    // Получениe характеристик камеры
                CameraCharacteristics cc = mCameraManager.getCameraCharacteristics(cameraID);
                                                                                                    // Получения списка выходного формата, который поддерживает камера
                StreamConfigurationMap configurationMap =
                        cc.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

                                                                                                    //  Определение какая камера куда смотрит
                int Faceing = cc.get(CameraCharacteristics.LENS_FACING);

                if (Faceing ==  CameraCharacteristics.LENS_FACING_FRONT)
                {
                    Log.i(LOG_TAG,"Camera with ID: "+cameraID +  "  is FRONT CAMERA  ");
                    frontCam=cameraID;

                    int i = Integer.parseInt(cameraID);

                  //  mCameraManager.openCamera(cameraID,mCameraCallback,null);

//                    mCameraManager.openCamera();
                }

                if (Faceing ==  CameraCharacteristics.LENS_FACING_BACK)
                {
                    Log.i(LOG_TAG,"Camera with: ID "+cameraID +  " is BACK CAMERA  ");
                    backCam=cameraID;
                }


                                                                                                    // Получения списка разрешений которые поддерживаются для формата jpeg
                Size[] sizesJPEG = configurationMap.getOutputSizes(ImageFormat.JPEG);

                if (sizesJPEG != null) {
                    for (Size item:sizesJPEG) {
                        Log.i(LOG_TAG, "w:"+item.getWidth()+" h:"+item.getHeight());
                    }
                }  else {
                    Log.i(LOG_TAG, "camera don`t support JPEG");
                }
            }
        }
        catch(CameraAccessException e){
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

}
