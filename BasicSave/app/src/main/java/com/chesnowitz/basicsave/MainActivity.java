package com.chesnowitz.basicsave;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.chesnowitz.basicsave", Context.MODE_PRIVATE);

        ArrayList<String> guitarPlayers = new ArrayList<>();

        guitarPlayers.add("Steve Chesnowitz");
        guitarPlayers.add("Eric Johnson");
        guitarPlayers.add("Pete Townsend");
        guitarPlayers.add("Buddy Guy");

        try {
            sharedPreferences.edit().putString("guitarPlayers", ObjectSerializer.serialize(guitarPlayers)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> newPlayers = new ArrayList<>();

        try {
            newPlayers = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString
                    ("guitarPlayers", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        sharedPreferences.edit().putString("username", "Steve").apply();
//
//        String username = sharedPreferences.getString("username", "");
//
//        Log.i("username", username);
        Log.i("newPlayers", newPlayers.toString());
        
    }
}
