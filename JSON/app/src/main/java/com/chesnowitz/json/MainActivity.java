package com.chesnowitz.json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetDataTask task = new GetDataTask();
        task.execute("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1");
    }

    public class GetDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = ""; //store results
            URL url; // stores the url
            HttpURLConnection urlConnection = null; // creates connection with name urlConnection

            try {
                url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream input = urlConnection.getErrorStream();

                InputStreamReader reader = new InputStreamReader(input);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        //onPostExecute is called when the background method is completed
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("UrL content", result);

            GetDataTask task = new GetDataTask();
            task.execute("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1");
        }
    }
}