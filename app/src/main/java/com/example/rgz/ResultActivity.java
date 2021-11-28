package com.example.rgz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    public static final String RESULT = "RESULT";

    TextView[] myRes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int[] res;
        res = intent.getIntArrayExtra(RESULT);
        myRes = new TextView[]{
                 (TextView) findViewById(R.id.res0),
                 (TextView) findViewById(R.id.res1),
                 (TextView) findViewById(R.id.res2),
                 (TextView) findViewById(R.id.res3),
                 (TextView) findViewById(R.id.res4),
                 (TextView) findViewById(R.id.res5),
                 (TextView) findViewById(R.id.res6),
                 (TextView) findViewById(R.id.res7),
                 (TextView) findViewById(R.id.res8),
                 (TextView) findViewById(R.id.res9),
                 (TextView) findViewById(R.id.res10),
                 (TextView) findViewById(R.id.res11),
                 (TextView) findViewById(R.id.res12),
                 (TextView) findViewById(R.id.res13),
                 (TextView) findViewById(R.id.res14),
                 (TextView) findViewById(R.id.res15),
                 (TextView) findViewById(R.id.res16),
                 (TextView) findViewById(R.id.res17),
                 (TextView) findViewById(R.id.res18),
                 (TextView) findViewById(R.id.res19),
                 (TextView) findViewById(R.id.res20),
                 (TextView) findViewById(R.id.res21),
                 (TextView) findViewById(R.id.res22),
                 (TextView) findViewById(R.id.res23),
                 (TextView) findViewById(R.id.res24),
                 (TextView) findViewById(R.id.res25),
                 (TextView) findViewById(R.id.res26),
                 (TextView) findViewById(R.id.res27),
                 (TextView) findViewById(R.id.res28),
                 (TextView) findViewById(R.id.res29),

        };
        for(int i = 0; i < res.length; i++){
            if(res[i] == 1000000) myRes[i].setText("none");
            else myRes[i].setText(Integer.toString(res[i]));
        }
    }
}