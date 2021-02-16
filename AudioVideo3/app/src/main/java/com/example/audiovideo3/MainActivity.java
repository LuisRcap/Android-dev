package com.example.audiovideo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button jbtn1, jbtn2;

    Intent itn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jbtn1 = (Button) findViewById(R.id.xbtn1);
        jbtn2 = (Button) findViewById(R.id.xbtn2);

        jbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, AudioActivity.class);
                startActivity(itn);
            }
        });

        jbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(itn);
            }
        });
    }
}