package com.example.applistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;              //Librerías necesarias para el funcionamiento del archivo
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    Button btnListas;      //Botón que se usará para la función de enviar los nombres y apellidos
    Intent itn;             //Intent que llamará los programas de las funciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListas = (Button) findViewById(R.id.bnListas);

        //Función del botón para invocar intent
        btnListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, ListaImagenes.class); //ligadura del intent con las clases que se usarán
                startActivity(itn);
            }
        });
    }
}