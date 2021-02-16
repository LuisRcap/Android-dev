package com.example.appcomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText jetNumero;      //Entradas de teclado que se utilizarán en la aplicación
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnIgual;      //Botones que se usarán para ingresar los datos
    Bundle bdlN;         //Los conjuntos que se usarán para enviar los datos por medio del intent
    Intent itn;             //Intent que llamará los programas de las funciones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jetNumero = (EditText) findViewById(R.id.etNumero);     //Se liga el edit text al XML

        //Se ligan los botones con los del XML
        btnIgual = (Button) findViewById(R.id.btnIgual);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        //Función del botón '=' para obtener el número del EditText
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, resultado.class); //ligadura del intent con las clases que se usarán
                bdlN = new Bundle();    //Declaración de un nuevo conjunto
                bdlN.putString("NUMERO", jetNumero.getText().toString());   //Cadena de texto de EDitText agregada al conjunto con id

                itn.putExtras(bdlN);        //Envío del conjunto a la otra clase
                startActivity(itn);         //Inicio de clase para iniciar su función
            }
        });

        //Función del botón '0' para poner un número en el EditText
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("0");
            }
        });

        //Función del botón '1' para poner un número en el EditText
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("1");
            }
        });

        //Función del botón '2' para poner un número en el EditText
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("2");
            }
        });

        //Función del botón '3' para poner un número en el EditText
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("3");
            }
        });

        //Función del botón '4' para poner un número en el EditText
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("4");
            }
        });

        //Función del botón '5' para poner un número en el EditText
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("5");
            }
        });

        //Función del botón '6' para poner un número en el EditText
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("6");
            }
        });

        //Función del botón '7' para poner un número en el EditText
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("7");
            }
        });

        //Función del botón '8' para poner un número en el EditText
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("8");
            }
        });

        //Función del botón '9' para poner un número en el EditText
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jetNumero.append("9");
            }
        });

    }
}