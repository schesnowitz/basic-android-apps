package com.chesnowitz.secondacttivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public void goToSecond(View view) {
        Log.i("Button", "Has been clicked");

        Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);

        intent.putExtra("username", "Steve"); // gets received in the on create method of FullScreenActivity
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
