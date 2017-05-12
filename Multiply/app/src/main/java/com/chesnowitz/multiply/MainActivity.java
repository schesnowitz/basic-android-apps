package com.chesnowitz.multiply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView timesTableListView;

    public void generateTimesTable(int timesTable) {


        ArrayList<String> timesTableContent = new ArrayList<String>();

        for (int i = 1; i <= 10; i++) {
            timesTableContent.add(Integer.toString(i * timesTable));
        }

        // add this to the list view

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                timesTableContent
        );

        timesTableListView.setAdapter(arrayAdapter);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar timesTableSeekBar = (SeekBar)findViewById(R.id.timesTableSeekBar);
        timesTableListView = (ListView)findViewById(R.id.timesTableListView);

        timesTableSeekBar.setMax(20); // there is no min setting
        timesTableSeekBar.setProgress(10); // sets the slider in the middle of 20

        //methods
//          calls the methods "listens"
        timesTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1; // setting the min
                int timesTable;
                if (progress < min ) {
                    timesTable  = min;
                    timesTableSeekBar.setProgress(min); // bar slides to one not zero
                } else {
                    timesTable = progress;
                }

                Log.i("SeekBar Value = ", Integer.toString(timesTable));
                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        int timesTable = 10;
//
//        ArrayList<String> timesTableContent = new ArrayList<String>();
//
//        for (int i = 1; i <= 10; i++) {
//            timesTableContent.add(Integer.toString(i * timesTable));
//        }
//
//        // add this to the list view
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                timesTableContent
//        );
//
//        timesTableListView.setAdapter(arrayAdapter);

        generateTimesTable(10); // see line 14
    }
}
