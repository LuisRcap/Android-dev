package com.example.appintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;              //Librerías necesarias para el funcionamiento del archivo
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText jetNombre, jetApellido, jeta, jetb, jetc;      //Entradas de teclado que se utilizarán en la aplicación
    Button btnNombres;      //Botón que se usará para la función de enviar los nombres y apellidos
    Button btnChicharronera;    //Botón que se usará para calcular la formula general de una ecuación de segundo grado
    Bundle bdlN, bdlC;         //Los conjuntos que se usarán para enviar los datos por medio del intent
    Intent itn;             //Intent que llamará los programas de las funciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jetNombre = (EditText) findViewById(R.id.xNombre);      //EditText para obtener el nombre
        jetApellido = (EditText) findViewById(R.id.xApellido);  //EditText para obtener el apellido
        btnNombres = (Button) findViewById(R.id.xbtnNombres);   //Botones asignados al xml
        btnChicharronera = (Button) findViewById(R.id.xbtnChicharronera);

        //Función del botón para obtener los nombres y apellido de los EditText
        btnNombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, SegundaActivity.class); //ligadura del intent con las clases que se usarán
                bdlN = new Bundle();    //Declaración de un nuevo conjunto
                bdlN.putString("NOMBRE", jetNombre.getText().toString());   //Cadena de texto de EDitText agregada al conjunto con id
                bdlN.putString("APELLIDO", jetApellido.getText().toString());
                itn.putExtras(bdlN);        //Envío del conjunto a la otra clase
                startActivity(itn);         //Inicio de clase para iniciar su función
            }
        });

        jeta = (EditText) findViewById(R.id.xa);        //EditText para obtener el número a
        jetb = (EditText) findViewById(R.id.xb);        //EditText para obtener el número b
        jetc = (EditText) findViewById(R.id.xc);        //EditText para obtener el número c

        //Función del botón para calcular la formula general
        btnChicharronera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, Chicharronera.class);   //Entradas de teclado que se utilizarán en la aplicación
                bdlC = new Bundle();//Declaración de un nuevo conjunto

                bdlC.putInt("NA", Integer.parseInt(jeta.getText().toString()));     /*Entero obtenido por EditText transformando la cadena
                                                                                      por un número entero y agregado el valor con un id al
                                                                                      conjunto de datos*/
                bdlC.putInt("NB", Integer.parseInt(jetb.getText().toString()));
                bdlC.putInt("NC", Integer.parseInt(jetc.getText().toString()));
                itn.putExtras(bdlC);        //Envío del conjunto a la otra clase
                startActivity(itn);         //Inicio de clase para iniciar su función
            }
        });
    }
}