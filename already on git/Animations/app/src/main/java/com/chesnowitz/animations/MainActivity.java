package com.chesnowitz.animations;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void truck1Fade(View view){
        ImageView truck1 = (ImageView) findViewById(R.id.truck1);
        ImageView truck2 = (ImageView) findViewById(R.id.truck2);

//        truck1.animate().alpha(0f).setDuration(2500);
//        truck2.animate().alpha(1f).setDuration(2500);
//        truck1.animate().rotation(180f).setDuration(2500);
        truck1.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2500);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
