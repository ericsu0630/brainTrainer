package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    View linearLayout, gridLayout, imageView, restartButton;
    TextView finalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        gridLayout = findViewById(R.id.gridLayout);
        imageView = findViewById(R.id.imageView);
        finalText = findViewById(R.id.finalScoreTextView);
        restartButton = findViewById(R.id.restartButton);

        final TextView timerText = findViewById(R.id.countDownTextView);
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                //update timer view
                String timeLeft = l / 1000 +"s";
                timerText.setText(timeLeft);
            }

            @Override
            public void onFinish() {

                linearLayout.setVisibility(View.GONE);
                gridLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                finalText.setVisibility(View.VISIBLE);
                TextView scoreText = findViewById(R.id.scoreTextView);
                String scoreStr = scoreText.getText().toString();
                finalText.setText("Your final score is " + scoreStr);
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
        linearLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        finalText.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        timer.start();
    }
}