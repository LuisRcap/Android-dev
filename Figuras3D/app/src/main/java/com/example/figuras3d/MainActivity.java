package com.example.figuras3d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbnt6;

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
        jbtn2 = (Button) findViewById(R.id.btn2);
        jbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Piramide3D");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        jbtn3 = (Button) findViewById(R.id.btn3);
        jbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Prisma3D");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        jbtn4 = (Button) findViewById(R.id.btn4);
        jbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Cono3D");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        jbtn5 = (Button) findViewById(R.id.btn5);
        jbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Esfera3D");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        jbnt6 = (Button) findViewById(R.id.btn6);
        jbnt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiguraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("FIGURA", "Cilindro3D");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}