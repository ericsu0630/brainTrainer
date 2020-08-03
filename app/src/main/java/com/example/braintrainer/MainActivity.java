package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView timerText = findViewById(R.id.countDownTextView);
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                //update timer view
                String timeLeft = String.valueOf(l/1000)+"s";
                timerText.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                View linearLayout = (View) findViewById(R.id.linearLayout);
                linearLayout.setVisibility(View.GONE);
                View gridlayout = (View) findViewById(R.id.gridLayout);
                gridlayout.setVisibility(View.GONE);
                View imageView = (View) findViewById(R.id.imageView);
                imageView.setVisibility(View.VISIBLE);
                TextView finalText = (TextView) findViewById(R.id.finalScoreTextView);
                finalText.setVisibility(View.VISIBLE);
                TextView scoreText = (TextView) findViewById(R.id.scoreTextView);
                String scoreStr = scoreText.getText().toString();
                finalText.setText("Your final score is " + scoreStr);
                View restartButton = (View) findViewById(R.id.restartButton);
                restartButton.setVisibility(View.VISIBLE);
            }
        };
    }

    public void checkAnswer(View view){
        //check answer
        String answer= view.getTag().toString();
        Log.i("info", answer);
        //update score
        //new question
        generateQuestion();
    }

    public void generateQuestion(){
        //generate question and answer
        //generate wrong answers
        //shuffle answers
        //fill options
    }

    public void goButton(View view){
        //generateQuestion();
        Button goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.GONE);
        View linearLayout = (View) findViewById(R.id.linearLayout);
        View gridLayout = (View) findViewById(R.id.gridLayout);
        linearLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timer.start();
    }
}