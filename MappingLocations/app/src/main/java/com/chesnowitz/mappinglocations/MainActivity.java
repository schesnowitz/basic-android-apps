package com.chesnowitz.mappinglocations;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> locations = new ArrayList<>();
    static ArrayList<LatLng> latLngs = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.chesnowitz.mappinglocations",
                Context.MODE_PRIVATE);

        ArrayList<String> lats = new ArrayList<>();
        ArrayList<String> lngs = new ArrayList<>();

        locations.clear();
        lats.clear();
        lngs.clear();
        latLngs.clear();
        try {
            locations = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString
                    ("locations", ObjectSerializer.serialize(new ArrayList<String>())));

            lats = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString
                    ("lats", ObjectSerializer.serialize(new ArrayList<String>())));

            lngs = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString
                    ("lngs", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (locations.size() > 0 && lats.size() > 0 && lngs.size() > 0) {
            if (locations.size() == lats.size() && lats.size() == lngs.size()) {
                for (int i = 0; i < lats.size(); i++) {
                    latLngs.add(new LatLng(Double.parseDouble(lats.get(i)), Double.parseDouble(lngs.get(i))));
                }
            }
        } else {
            locations.add("Enter a location...");
            latLngs.add(new LatLng(0, 0));
        }



        arrayAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, locations);


        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("LocationID", position);
                startActivity(intent);
            }
        });


    }
}
