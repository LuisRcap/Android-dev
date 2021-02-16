package com.example.apparchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String s;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    Intent itn;
    Button btnArchivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.texto);
        tv.append("\nAbriendo: res/raw/misdatos.txt");
        is = getResources().openRawResource(R.raw.misdatos);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr, 8192);
        try{
            while( null != (s=br.readLine()) )
                tv.append("\n" + s);
            is.close();
            isr.close();
            br.close();
        } catch(Exception e){
            tv.append("\n " + e);
        }
        tv.append("\nEnd of file.");

        btnArchivos = (Button) findViewById(R.id.xbtnArchivo);

        btnArchivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    itn = new Intent(MainActivity.this, ArchivosActivity.class);
                    startActivity(itn);
                }
                catch (Exception e)
                {
                    tv.append("\n\n"+ String.valueOf(e));
                }

            }
        });

    }
}