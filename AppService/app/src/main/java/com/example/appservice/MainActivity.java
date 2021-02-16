package com.example.appservice;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TextView jtv;
    private Button jbn;
    public int estadoJbn = 0;
    private static double tr = 0;
    public static boolean bandera = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jtv = (TextView) findViewById(R.id.xtvT);
        jbn = (Button) findViewById(R.id.xbnI);

        jbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estadoJbn == 0)
                {
                    initCrono();
                    estadoJbn = 1;
                    jbn.setText("Pausar");
                }
                else if(estadoJbn == 1)
                {
                    pausar(v);
                    estadoJbn = 2;
                }
                else if(estadoJbn == 2)
                {
                    pausar(v);
                    estadoJbn = 1;
                }
            }
        });

        Button stopButton = (Button) findViewById(R.id.xbnT);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jbn.setText("Iniciar");
                tr = 0;
                estadoJbn = 0;
                stopCrono();
            }
        });

        MiCrono.setUpdateListener(this);
    }

    @Override
    protected void onDestroy() {
        stopCrono();
        super.onDestroy();
    }
    private void initCrono() {
        bandera = true;
        Intent in = new Intent(MainActivity.this, MiCrono.class);
        startService(in);
    }

    private void stopCrono() {
        Intent in = new Intent(MainActivity.this, MiCrono.class);
        refreshCrono(0);
        stopService(in);
    }
    public void refreshCrono(double t) {
        jtv.setText(String.format("%.2f", t) + " segs");
        tr = t;
    }

    public void pausar(View view)
    {
        bandera = ! bandera;
        if(bandera)
        {
            jbn.setText("Pausar");
        }
        else
        {
            jbn.setText("Continuar");
        }
    }

}