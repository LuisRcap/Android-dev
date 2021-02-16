package com.example.ordenamientopormezcla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    TextView jtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jtv = (TextView) findViewById(R.id.xtv);

        Mezcla mezcla = new Mezcla();
        int arr [] = {5, 26, 53, 45, 3, 7, 15};
        int  n = arr.length;

        jtv.append("Arreglo original: ");
        for(int value: arr)
        {
            jtv.append(value + " ");
        }

        jtv.append("\n");

        jtv.append("Arreglo ordenado: ");

        try {
            mezcla.ordenar(arr, 0, n-1);
        }
        catch (Exception e)
        {
            jtv.append("\n" + String.valueOf(e) + "\n");
        }

        int arr2[] = mezcla.darArreglo(arr);

        n = arr2.length;
        for(int i=0; i<n; i++)
        {
            jtv.append(arr[i] + " ");
        }
        jtv.append("\n");
    }
}