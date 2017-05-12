package com.chesnowitz.spanish8onandroid;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked (View view) {

        int id = view.getId(); // gets the id created by android studio which is a int
        String stringId = ""; // create empty string where the id we assign can be filled

        stringId = view.getResources().getResourceEntryName(id);
        int resourceId = getResources().getIdentifier(stringId, "raw", "com.chesnowitz.spanish8onandroid");
         // resourceId gets the file from folder.  we set to our stringId then the dir then the app name*//

        MediaPlayer mp = MediaPlayer.create(this, resourceId); // here we call the resource id
        mp.start();
//        Log .i("Button Clicked", Integer.toString(view.getId())); // shows the id created by android studio
        Log .i("Button Clicked", stringId);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
