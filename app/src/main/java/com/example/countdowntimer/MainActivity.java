package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    private boolean running;
    TextView countDownDisplay;
    Button countDownButton;
    private long timeLeftInMilliSeconds = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownDisplay = (TextView) findViewById(R.id.countDownTimer);
        countDownButton = (Button) findViewById(R.id.button);

        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
    }

    public void startStop() {
        if (running) {
            stop();
        }
        else {
            start();
        }
    }

    public void start() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliSeconds = millisUntilFinished;
                updateTime();
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "Time Up!!!", Toast.LENGTH_SHORT).show();
            }
        }.start();
        running = true;
        countDownButton.setText("Pause");
    }
    public void stop() {
        countDownTimer.cancel();
        running = false;
        countDownButton.setText("Start");
    }

    public void updateTime() {
        int minutes = (int) timeLeftInMilliSeconds / 60000;
        int seconds=  (int) (timeLeftInMilliSeconds % 60000) / 1000;

        String timeLeft;

        timeLeft = minutes + ":";
        if (seconds < 10) {
            timeLeft += "0";
        }
        timeLeft += seconds;
        countDownDisplay.setText(timeLeft);
    }

}