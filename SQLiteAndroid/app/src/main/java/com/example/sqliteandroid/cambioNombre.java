package com.example.sqliteandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class cambioNombre extends AppCompatActivity{

    TextView jtv1, jtv2;   //TextView con el que se mostrará la información en la pantalla
    EditText jetn;
    Button jbnc;
    Bundle bdl;     //Conjunto al que se le cargaran los datos ingresados en el MainActivity
    SQLiteDatabase sqld;
    @Override
    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.cambio_nombre);
        jtv1 = (TextView) findViewById(R.id.xtvCN1);
        jtv2 = (TextView) findViewById(R.id.xtvCN2);
        jetn = (EditText) findViewById(R.id.xetCNN);
        jbnc = (Button) findViewById(R.id.xbnCN);
        bdl = getIntent().getExtras();

        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBContactos", null, 1);
        sqld = dsqlh.getWritableDatabase();

        jetn.setText(bdl.getString("NOMBRE"));

        jbnc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] nombre, id;
                nombre = new String[1];
                id = new String[1];
                id[0] = bdl.getString("ID");
                nombre[0] = jetn.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nombre", nombre[0]);
                cv.put("id", bdl.getString("ID"));
                try
                {
                    sqld.update("Contactos", cv, "id=?", id);
                    jtv2.setText("El registro se actualizó existosamente");
                }
                catch(Exception e)
                {
                    jtv2.setText(String.valueOf(e) + "\nNo fue posible actualizar el registro");
                }
            }
        });
    }
}
