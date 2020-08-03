package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    CountDownTimer timer;
    Button goButton;
    View linearLayout, gridLayout, imageView, restartButton;
    TextView finalText, option1, option2, option3, option4, scoreText;
    String s;
    int score, questionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearLayout);
        gridLayout = findViewById(R.id.gridLayout);
        imageView = findViewById(R.id.imageView);
        finalText = findViewById(R.id.finalScoreTextView);
        restartButton = findViewById(R.id.restartButton);
        option1 = findViewById(R.id.option1TextView);
        option2 = findViewById(R.id.option2TextView);
        option3 = findViewById(R.id.option3TextView);
        option4 = findViewById(R.id.option4TextView);
        scoreText = findViewById(R.id.scoreTextView);
        goButton = findViewById(R.id.goButton);

        final TextView timerText = findViewById(R.id.countDownTextView);
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                String timeLeft = l / 1000 +"s";
                timerText.setText(timeLeft);
            }

            @Override
            public void onFinish() {

                linearLayout.setVisibility(View.GONE);
                gridLayout.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                finalText.setVisibility(View.VISIBLE);
                String scoreStr = scoreText.getText().toString();
                finalText.setText("Your final score is " + scoreStr);
                restartButton.setVisibility(View.VISIBLE);
            }
        };
    }

    public void checkAnswer(View view){

        TextView clickedView = (TextView) view;
        String answer = clickedView.getText().toString();
        questionCount++;
        Log.i("selected/correct",answer+"/"+s);
        if(answer.equalsIgnoreCase(s)){
            score++;
        }
        scoreText.setText(score+"/"+questionCount);
        generateQuestion();
    }

    public void generateQuestion(){
        int num1, num2, shuffle, sum;
        String n1,n2;
        //generate question
        num1 = (int)(Math.random()*50); // random number from 0 to 50
        n1 = String.valueOf(num1);
        num2 = (int)(Math.random()*50); //random number from 0 to 50
        n2 = String.valueOf(num2);
        sum = num1 + num2;
        s = String.valueOf(sum);
        TextView question = findViewById(R.id.questionTextView);
        question.setText(n1 + " + " + n2 +" = ?" );
        //generate array of 4 wrong answers
        String[] options = new String[4];
        for(int i = 0; i<options.length; i++){
            do { //in case generated number is same as answer
                options[i] = String.valueOf((int) (Math.random() * 99));
            }while (options[i].equals(s));
        }
        //replace a random value with the right answer
        Random r = new Random();
        shuffle = r.nextInt(4); //random number from 0 to 3
        options[shuffle] = s;
        //fill textView options
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);
        option4.setText(options[3]);

    }

    public void goButton(View view){
        score = 0;
        questionCount = 0;
        scoreText.setText(R.string.score_text);
        goButton.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        finalText.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        generateQuestion();
        timer.start();
    }
}