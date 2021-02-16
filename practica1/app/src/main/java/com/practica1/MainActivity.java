package com.practica1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    //En esta sección se delcaran algunas variables globales que se estarán utilizando
    TextView jtv1; //Esta se usará para mostrar la información en la aplicación
    EditText jetN; //Esta se usará para obtener el dato que igrese el usuario
    int x = 0;     //En esta variable se guardará el número que proporsione el usuario
    String res;    //En este String se irán almacenando los numeros generados por cada operación


    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        Button btnIngresar = (Button) findViewById(R.id.boton); //Este botón es para tomar el dato escrito por el usuario vinculado al id del botón en el XML
        jetN = (EditText) findViewById(R.id.etNumero);          //Se vincula con el EditText del XML
        jtv1 = (TextView) findViewById(R.id.xtv1);              //Se vincula con el TextView del XML

        //Se genera la acción que se ejecutará al presionar el botón
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = Integer.parseInt(jetN.getText().toString()); //Se convierte el numero de String a entero
                NumMaravilloso(x);                               //Se llama a la función para evaluar el "número maravilloso"
            }
        });


        jtv1.setMovementMethod(new ScrollingMovementMethod());  //Esta función nos permitirá hacer scroll para ver todos los datos


    }

    //Se declara la función para calcular el número maravilloso
    public void NumMaravilloso(int a)
    {
        //Cálculos
        res = "";
        while (a != 1)
        {
            //Los condicionales son para evaluar si los números son pares o impares, si el resultado de %2 es 0, el número es par, de lo contrario es impar
            if(a%2 != 0)
            {
                a = a*3 + 1;

                res = res + "\n"+ a;    //Aquí se van agregando los nuevos resultados a la cadena para después ser mostrados
            }
            else if(a%2 == 0)
            {
                a = a/2;
                res = res + "\n"+ a;    //Aquí se van agregando los nuevos resultados a la cadena para después ser mostrados
            }
        }


        //Aquí se muestran todos los datos en pantalla
        jtv1.setText("Los numeros son "+ res + "\nEl " + x +" es un número maravilloso");


    }
}