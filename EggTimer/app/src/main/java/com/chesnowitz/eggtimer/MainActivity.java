package com.chesnowitz.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {



    SeekBar eggSeekBar;
    TextView eggTimerText;
    Boolean counterActive = false;
    Button timerButton;
    CountDownTimer countDownTimer;

    public void updateTheTimer(int secondsLeft) {
        int minutes = ((int) secondsLeft / 60);
        int seconds = secondsLeft - minutes * 60;

        String minutesToString = Integer.toString(minutes);

        String secondsToString = Integer.toString(seconds);


        if (seconds <= 9) {
            secondsToString = "0" + secondsToString;
        }

        Log.i("Slider Seconds = ", secondsToString);
        eggTimerText.setText(minutesToString + ":" + secondsToString);
    }


    public void timerBuntonFunction(View view) {
        if (counterActive == false) {
            counterActive = true;
            eggSeekBar.setEnabled(false);
            timerButton.setText("Stop");
            // add 100 ms here to allow for the time for script to run
            countDownTimer = new CountDownTimer(eggSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTheTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
//                usual way to call would be (this, R.raw.airhorm) but this here refers to CountDownTimer
                    MediaPlayer fogHornPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorm);
                    fogHornPlayer.start();
                    Log.i("The Time", "is done");

                    eggTimerText.setText("0.00");
                    eggTimerText.setText("0:30");
                    eggSeekBar.setProgress(30);
                    countDownTimer.cancel();
                    timerButton.setText("Go!");
                    eggSeekBar.setEnabled(true);
                    counterActive = (false);

                }
            }.start();
        } else {
            eggTimerText.setText("0:30");
            eggSeekBar.setProgress(30);
            countDownTimer.cancel();
            timerButton.setText("Go!");
            eggSeekBar.setEnabled(true);
            counterActive = (false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eggSeekBar = (SeekBar) findViewById(R.id.eggSeekBar);
        eggTimerText = (TextView) findViewById(R.id.eggTimeText);
        eggSeekBar.setMax(600);
        eggSeekBar.setProgress(30);
        timerButton = (Button)findViewById(R.id.timerButton);
        eggSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            updateTheTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
