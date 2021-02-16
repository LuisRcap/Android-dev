package com.example.apparchivos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.app.*;
import android.widget.*;
import java.io.*;

public class ArchivosActivity extends AppCompatActivity{

    TextView jtv;
    File f, d, r;
    FileOutputStream fos;
    PrintWriter pw;
    String s;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.archivos_activity);
        jtv = (TextView) findViewById(R.id.xtv);
        try {
            s = Environment.getExternalStorageState(); // Tarjeta SD disponible.
        }
        catch (Exception e)
        {
            jtv.append("\n\n"+ String.valueOf(e));
        }

        jtv.append("\nEstado Actual: " + s);
        r = Environment.getExternalStorageDirectory();// Directorio en tarjeta SD.
        d = new File(r.getAbsolutePath() + "/datos"); // Crea carpeta datos en SD.
        d.mkdirs();
        jtv.append("\n\nNueva Carpeta: " + d + "\n\nContenido de la Carpeta " + r + ":");
        String [] lista = r.list();
        for(int i=0; i<lista.length; i++)
            jtv.append("\n" + lista[i]);
        f = new File(d, "misdatos.txt"); // Abre datos1.txt en carpeta datos.
        try{
            fos = new FileOutputStream(f);
            pw = new PrintWriter(fos);
            pw.println("Inicio del archivo"); // Escribe cadena en datos1.txt.
            pw.flush();
            pw.close();
            jtv.append("\n\n Archivo: " + f + "\nGuardado.");
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();;
            jtv.append("\n\nEXCEPTION:\n" + fnfe);
        }
    }
}
