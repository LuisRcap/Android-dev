package com.example.appintent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class Chicharronera extends  AppCompatActivity{
    TextView jtv;       //TextView con el que se mostrará la información en la pantalla
    Bundle bdl;         //Conjunto en el que se cargaran los datos obtenidos por MainActivity
    double resa, resb, resr;    //Números a los que se les asignarán los cálculos
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_chicharronera);
        jtv = (TextView) findViewById(R.id.xtv1);       //Ligadura del textview con el xml
        bdl = getIntent().getExtras();      //Obtención de los valores de la ecuación en el bundle

        resr = Math.pow(bdl.getInt("NB"), 2) - 4*bdl.getInt("NA")*bdl.getInt("NC"); //Cálculo de lo que hay dentro de la raíz

        //Evaluación si la ecuación tiene solución real o no
        if(resr < 0)
        {
            jtv.setText("Dado que la raíz de b^2-4ac es < 0: " + resr+ "\nLa ecuación no tiene solución");
        }
        else if(resr >= 0) {
            resa = (-bdl.getInt("NB") + Math.sqrt(resr)) / (2 * bdl.getInt("NA"));      //Cálculo de la formula general 1
            resb = (-bdl.getInt("NB") - Math.sqrt(resr)) / (2 * bdl.getInt("NA"));      //Cálculo de la formula general 2
            //Mostrando los resultados obtenidos en la ecuación
            jtv.setText("El resultado de " +bdl.getInt("NA") +"x^2 + "+bdl.getInt("NB")+ "x + " +bdl.getInt("NC") +"= 0 es:\nx1 = " + resa + "\nx2 = " + resb );
        }
    }
}
