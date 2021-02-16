package com.example.aplicacionvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button jbtn1, jbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jbtn1 = (Button) findViewById(R.id.btn1);
        jbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Cubo3D");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}