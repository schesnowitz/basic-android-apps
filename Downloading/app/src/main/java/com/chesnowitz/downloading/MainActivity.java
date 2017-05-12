package com.chesnowitz.downloading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    /*
        1: first is what we are sending to the class in this case String

        2: Void is the method we may use to show the progress of the task
            if downloading large content for example we may have a second method that shows progress bar
            this is where the name of that method would go

        3: and the last value is what we are expecting to get returned from our task
    */

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read(); //keeps track of data characters

                while(data != -1) {
                    char current = (char) data; //because data is int we call char

                    result += current;  // append to result

                    data = reader.read();   // get data to continue reading... contines the loop again
                                            // value here will be -1

                }
                return result;
            }
            catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute("http://chesnowitz.com/").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.i("Contents of URL", result);
    }
}
