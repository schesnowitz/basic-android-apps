package com.chesnowitz.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView appListView = (ListView) findViewById(R.id.appListView);

        final ArrayList<String> myFamily = new ArrayList<String>();

        myFamily.add("Addi");
        myFamily.add("Layla");
        myFamily.add("Kathy");
        myFamily.add("Mum");

//      this converts our Array into a list view there are many styles we are using simple_list_item_1
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myFamily
        );

        appListView.setAdapter(arrayAdapter); // here we assign the array adaptor to our view

        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override    // AdapterView could be anything its generic with the name parent
                         // View view, represents the view anthing in the "view" in this case the row
                         // position represent individual items in the view here row item
                        // id gets the id but here we will only use position
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("name clicked", myFamily.get(position));
//                parent.setVisibility(View.GONE);  // here we are calling parent to make whole view disappea

            }
        });


    }
}
