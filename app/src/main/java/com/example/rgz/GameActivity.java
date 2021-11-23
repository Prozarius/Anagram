package com.example.rgz;

import static com.example.rgz.MainActivity.LEVEL;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public String level;
    public String t[] = {};
    int seconds, i;
    long start_time;
    long finish_time;

    final Handler myhandler = new Handler();

    public static final String TIME = "TIME";

    TextView anagram, mTimer;
    EditText word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        anagram = (TextView) findViewById(R.id.textView);
        mTimer = (TextView) findViewById(R.id.time);
        word = (EditText) findViewById(R.id.word);

        Intent intent = getIntent();
        level = intent.getStringExtra(LEVEL);

        start_time =  System.currentTimeMillis();
        switch (level){
            case "1":
                finish_time = start_time + 8000;
                seconds = 8;
                break;
            case "2":
                finish_time = start_time + 9000;
                seconds = 9;
                break;
            case "3":
                finish_time = start_time + 10000;
                seconds = 10;
                break;
            case "4":
                finish_time = start_time + 11000;
                seconds = 11;
                break;
            case "5":
                finish_time = start_time + 12000;
                seconds = 11;
                break;
            case "6":
                finish_time = start_time + 13000;
                seconds = 12;
                break;
            case "7":
                finish_time = start_time + 14000;
                seconds = 13;
                break;
            case "8":
                finish_time = start_time + 15000;
                seconds = 14;
                break;
            case "9":
                finish_time = start_time + 16000;
                seconds = 15;
                break;
            case "0":
                finish_time = start_time + 17000;
                seconds = 16;
                break;
            default:
                finish_time = start_time;
                seconds = 0;
                break;
        }

        int min = 1;
        int max = 10;
        int diff = max - min;
        Random random = new Random();
        i = random.nextInt(diff + 1);
        i += min;

        AssetManager assManager = getApplicationContext().getAssets();
        InputStream fis = null;
        String line = "";
        try {
            fis = assManager.open("levels.txt");
            BufferedReader bfr = new BufferedReader(new InputStreamReader(fis));
            while ((line = bfr.readLine()) != null) {
                t = line.split(" ");
                if(t[0].equals(level)){
                    anagram.setText(t[i].replaceAll("(.)(.)", "$2$1"));
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        myTimer();
    }

    public void CheckWord(View v){
        String answer = word.getText().toString();
        Log.d("TAG_LOG", "поиск");
        //Toast.makeText(this, t[i] + " " + answer, Toast.LENGTH_SHORT).show();
        if(answer.equals(t[i])){
            myhandler.removeCallbacks(null);
            Intent data = new Intent(this, MainActivity.class);
            int t = (int) (System.currentTimeMillis() - start_time);
            data.putExtra(TIME, t);
            Toast.makeText(this, Integer.toString(t), Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, data);
            finish();
        }
        else {
            Toast.makeText(GameActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
    }


    private void myTimer() {
        myhandler.post(new Runnable() {
            @Override
            public void run() {
                if(System.currentTimeMillis() < finish_time){
                    mTimer.setText("You have " + seconds +" seconds");
                    seconds--;
                    myhandler.postDelayed(this, 1000);
                }
                else{
                    Intent data = new Intent(GameActivity.this, MainActivity.class);
                    data.putExtra(TIME, System.currentTimeMillis());

                    setResult(RESULT_CANCELED, data);
                    finish();
                }
            }
        });
    }
}