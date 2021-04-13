package com.example.sotiks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;



public class VoiceClass {
        public MediaPlayer mPlayer;
        public Context main;

        public VoiceClass(Context context){
            this.main = context;
        }
        public Intent onClickSpeach(){
         Intent intent  = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
         intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
         return intent;
     }


    public void createPlayer () {
        mPlayer= MediaPlayer.create(main, R.raw.music);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlayer.stop();
            }
        });
    }

     public void startPlayer(){
         if (mPlayer.isPlaying()){
             mPlayer.pause();
         }else {

             mPlayer.start();
         }
     }
     public void stopPlayer(){
         if (mPlayer.isPlaying()) {
             mPlayer.stop();
         }
     }



}
