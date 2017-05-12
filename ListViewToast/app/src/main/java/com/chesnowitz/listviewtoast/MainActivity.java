package com.chesnowitz.listviewtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//              (id of the view but could be anything)    (here we are finding the view by its id)
        ListView playerListView = (ListView) findViewById(R.id.playerListView);

        final ArrayList<String> playerList = new ArrayList<String>(); // initiates a method ()

        playerList.add("Eric Clapton");
        playerList.add("Mark Knopfler");
        playerList.add("Richard Thompson");



        // we need to give format by android.R.layout.  afert the dot we get a bunch of options
        // then we call the array
        ArrayAdapter<String> playerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                playerList);

        playerListView.setAdapter(playerArrayAdapter);

        // create a listener of the list view ...
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                line below works...
//                Toast.makeText(MainActivity.this, "" + playerList.get(position), Toast.LENGTH_SHORT).show();

//                also can use

                Toast.makeText(getApplicationContext(), playerList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
