package com.chesnowitz.timerdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new CountDownTimer(10000, 1000) {
            // what happens every tick every tick
            public void onTick(long millisecondsTilDone){
                Log.i("Seconds", String.valueOf(millisecondsTilDone / 1000));
            }

            public void onFinish() {
                // what happens when finished
                Log.i("This", "Count is done");
            }
        }.start();


        final Handler handler = new Handler(); // allowes code to be run on delay handles code in Runnable
        Runnable run = new Runnable() {
            @Override
            public void run() {
                // this code runs every x seconds

                Log.i("Steve is a great guy", "this is every 2.5 secods");
                handler.postDelayed(this, 2500);
            }
        };
        handler.post(run);
    }
}
