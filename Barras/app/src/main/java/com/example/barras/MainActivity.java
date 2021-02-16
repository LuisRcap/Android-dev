package com.example.barras;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.RatingBar.*;

public class MainActivity extends AppCompatActivity {

    RatingBar jrb1;
    TextView jtv3;
    Button jbn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnRatingBar();
        addListenerOnButton();

    }

    public void addListenerOnRatingBar() {
        jrb1 = (RatingBar) findViewById(R.id.xrb1);
        jtv3 = (TextView) findViewById(R.id.xtv3);
        jrb1.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rb, float f, boolean bo) {
                jtv3.setText(String.valueOf(f));
            }
        });
    }
    public void addListenerOnButton() {
        jrb1 = (RatingBar) findViewById(R.id.xrb1);
        jbn1 = (Button) findViewById(R.id.xbn1);
        jbn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Evaluación: " + jrb1.getRating(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}