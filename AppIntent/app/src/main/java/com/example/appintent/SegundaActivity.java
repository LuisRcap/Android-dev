package com.example.appintent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class SegundaActivity extends AppCompatActivity{
    TextView jtv;   //TextView con el que se mostrará la información en la pantalla
    Bundle bdl;     //Conjunto al que se le cargaran los datos ingresados en el MainActivity
    String res;     //Respuesta en la que se guardarán las cadenas del bundle

    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_segunda);
        jtv = (TextView) findViewById(R.id.xtv1);   //Ligadura del textview del xml
        bdl = getIntent().getExtras();      //Obtención de los datos al bundle
        res = bdl.getString("NOMBRE") + " " + bdl.getString("APELLIDO"); //Guardando las cadenas nombre y apellido en la respuesta
        jtv.setText("Hola " + res);     //Saludo con el nombre ingresado en la aplicación
    }
}
