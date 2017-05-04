package com.chesnowitz.imageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void changeImage(View view) {

        ImageView image = (ImageView) findViewById(R.id.truck1);
        image.setImageResource(R.drawable.truck2);

        Log.i("Button", "It has been clicked");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
