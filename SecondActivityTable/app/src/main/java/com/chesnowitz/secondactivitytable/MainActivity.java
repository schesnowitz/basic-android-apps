package com.chesnowitz.secondactivitytable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        final ArrayList<String> guitarists = new ArrayList<String>();

        guitarists.add("Eric Clapton");
        guitarists.add("Mark Knopfler");
        guitarists.add("Richard Thompson");
        guitarists.add("Eric Johnson");

        ArrayAdapter arrayAdapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, guitarists);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("name", guitarists.get(position));

                startActivity(intent);
            }
        });
    }
}
