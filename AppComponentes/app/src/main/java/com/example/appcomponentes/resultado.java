package com.example.appcomponentes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.*;

public class resultado extends AppCompatActivity{
   //En esta sección se delcaran algunas variables globales que se estarán utilizando
    TextView jtv1, jtv2; //Esta se usará para mostrar la información en la aplicación
    int x = 0;     //En esta variable se guardará el número que proporsione el usuario
    String res;    //En este String se irán almacenando los numeros generados por cada operación
    Bundle bdl;     //Conjunto al que se le cargaran los datos ingresados en el MainActivity

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.resultado);

        Button btnPrimo = (Button) findViewById(R.id.botonPrimo); //Este botón es para tomar el dato escrito por el usuario vinculado al id del botón en el XML
        Button btnFibo = (Button) findViewById(R.id.botonFibo); //Este botón es para tomar el dato escrito por el usuario vinculado al id del botón en el XML
        Button btnPalindromo = (Button) findViewById(R.id.botonPalindromo); //Este botón es para tomar el dato escrito por el usuario vinculado al id del botón en el XML
        Button btnMaravilloso = (Button) findViewById(R.id.botonMaravilloso); //Este botón es para tomar el dato escrito por el usuario vinculado al id del botón en el XML
        jtv1 = (TextView) findViewById(R.id.xtv1);              //Se vincula con el TextView del XML
        jtv2 = (TextView) findViewById(R.id.xtvNum);
        bdl = getIntent().getExtras();      //Obtención de los datos al bundle

        jtv1.setText("¿Qué desea evaluar con el número " + bdl.getString("NUMERO") + "?\n");

        //Se genera la acción que se ejecutará al presionar el botón
        btnPrimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = Integer.parseInt(bdl.getString("NUMERO")); //Se convierte el numero de String a entero
                evaluarPrimo(x);                               //Se llama a la función para evaluar el "número maravilloso"
            }
        });

        //Se genera la acción que se ejecutará al presionar el botón
        btnFibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = Integer.parseInt(bdl.getString("NUMERO")); //Se convierte el numero de String a entero
                evaluarFibo(x);                               //Se llama a la función para evaluar el "número maravilloso"
            }
        });

        //Se genera la acción que se ejecutará al presionar el botón
        btnPalindromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluarPalindromo(bdl.getString("NUMERO"));      //Se llama a la función para evaluar el "número maravilloso"
            }
        });

        btnMaravilloso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = Integer.parseInt(bdl.getString("NUMERO")); //Se convierte el numero de String a entero
                NumMaravilloso(x);                               //Se llama a la función para evaluar el "número maravilloso"
            }
        });


        jtv2.setMovementMethod(new ScrollingMovementMethod());  //Esta función nos permitirá hacer scroll para ver todos los datos


    }

    //Se declara la función para evaluar si el número es primo o no
    public void evaluarPrimo(int a)
    {
        int contador = 0;

        for(int i = 1; i <= a; i++)
        {
            //Se evalúa cúantas veces la división entre cada número, el residuo es 0
            if(a%i == 0)
            {
                contador++;
            }
        }

        //Se evalúa si el número es o no primo
        if (contador == 2)
        {
            jtv2.setText("El número "+ x +" es primo");
        }
        else
        {
            jtv2.setText("El número "+ x +" no es primo");
        }

        x = 0;
    }

    //Se declara la función para evaluar si el número es primo o no
    public void evaluarFibo(int a)
    {
        boolean evaluacionTerminada = false;
        int suma = 0, num1 = 0, num2 = 1;
        res = "\n"+ num1 + "\n"+ num2;    //Aquí se van agregando los nuevos resultados a la cadena para después ser mostrados

        //Se realizan las operaciones para evaluar cada número Fibo
        while(evaluacionTerminada == false)
        {
            suma = num1 + num2;
            num1 = num2;
            num2 = suma;
            res = res + "\n"+ suma;    //Aquí se van agregando los nuevos resultados a la cadena para después ser mostrados

            //Se evalua si el número proporcionado es o no un número Fibo
            if(suma == a)
            {
                //Aquí se muestran todos los datos en pantalla
                jtv2.setText("Los numeros Fibonacci son  "+ res + "\nEl " + x +" es un número Fibonacci");
                evaluacionTerminada = true;
            }
            else if(suma > a)
            {
                //Aquí se muestran todos los datos en pantalla
                jtv2.setText("Los numeros Fibonacci son  "+ res + "\nEl " + x +" no es un número Fibonacci");
                evaluacionTerminada = true;
            }
        }
    }

    //Se declara la función para evaluar si el número es primo o no
    public void evaluarPalindromo(String a)
    {
        x = Integer.parseInt(a);
        res = "";

        //Se agregan los números proporcionados de forma inversa
        for (int i = a.length()-1; i >= 0; i--)
        {
            res += a.charAt(i);
        }

        //Se evalúa si el número es o no Palíndromo
        if(x == Integer.parseInt(res))
        {
            jtv2.setText("El número "+ a + " es palíndromo");
        }
        else
        {
            jtv2.setText("El número "+ a + " no es palíndromo");
        }
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
        jtv2.setText("Los numeros son "+ res + "\nEl " + x +" es un número maravilloso");

    }
}
