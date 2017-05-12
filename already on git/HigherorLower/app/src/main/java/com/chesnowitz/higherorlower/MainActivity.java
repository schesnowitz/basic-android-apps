package com.chesnowitz.higherorlower;
import java.util.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int  randomNumber;

    public void makeToast(String string){
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void guessValue(View view) { // onClick function of button = guessValue


        EditText guessEditTextArea = (EditText) findViewById(R.id.guessEditTextArea);
        int guessInt = Integer.parseInt(guessEditTextArea.getText().toString());




        if (guessInt > randomNumber) {
            makeToast("Lower");

        } else if (guessInt < randomNumber) {
            makeToast("Higher");

        } else {
            makeToast("You Got It! Try Again...");

            Random rand = new Random();
            randomNumber = rand.nextInt(10) + 1;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        randomNumber = rand.nextInt(20) + 1;
    }
}
