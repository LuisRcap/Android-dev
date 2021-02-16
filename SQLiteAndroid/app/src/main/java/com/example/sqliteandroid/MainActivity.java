package com.example.sqliteandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;



public class MainActivity extends AppCompatActivity {

    EditText jetI, jetN;
    Button jbnA, jbnL, jbnB, jbnC;
    TextView jtvL;
    SQLiteDatabase sqld;
    int lastId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jetI = (EditText) findViewById(R.id.xetI);
        jetN = (EditText) findViewById(R.id.xetN);
        jbnA = (Button) findViewById(R.id.xbnA);
        jbnL = (Button) findViewById(R.id.xbnL);
        jbnB = (Button) findViewById(R.id.xbnB);
        jbnC = (Button) findViewById(R.id.xbnC);
        jtvL = (TextView) findViewById(R.id.xtvL);
        DbmsSQLiteHelper dsqlh = new DbmsSQLiteHelper(this, "DBContactos", null, 1);
        sqld = dsqlh.getWritableDatabase();

        //Intento para lanzar id en el campo de texto id
        try
        {
            Cursor cid;

            cid = sqld.rawQuery("SELECT id FROM Contactos", null); //Se consultan los id de la base de datos
            if(cid.moveToLast())
            {
                int Iid = Integer.parseInt(cid.getString(0)) + 1;
                lastId = Iid;
                jetI.setText(String.valueOf(Iid));
            }
            else
            {
                lastId = 1;
                jetI.setText("1");
            }
        }
        catch (Exception e)
        {
            jtvL.setText(String.valueOf(e));
        }



        jbnA.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                String nombre = jetN.getText().toString();
                ContentValues cv = new ContentValues();
                if(!jetN.getText().toString().isEmpty())
                {
                    cv.put("id", lastId);
                    cv.put("nombre", nombre);
                    sqld.insert("Contactos", null, cv);
                    Cursor cid;
                    cid = sqld.rawQuery("SELECT id FROM Contactos", null);
                    try
                    {
                        if(cid.moveToLast())
                        {
                            int Iid = Integer.parseInt(cid.getString(0)) + 1;
                            lastId = Iid;
                            jetI.setText(String.valueOf(Iid));
                        }

                    }
                    catch (Exception e)
                    {
                        jtvL.setText(String.valueOf(e));
                    }
                    jetN.setText("");
                }
                else
                {
                    jtvL.setText("No se agregó el registro, hace falta el nombre.");
                }
            }
        });


        jbnL.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String id, nombre;
                Cursor c = sqld.rawQuery("SELECT id,nombre FROM Contactos", null);
                jtvL.setText("");
                if (c.moveToFirst()) {
                    do {
                        id = c.getString(0);
                        nombre = c.getString(1);
                        jtvL.append(" " + id + "\t" + nombre + "\n");
                    }while(c.moveToNext());
                }
            }
        });

        jbnC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] id, nombre;
                nombre = new String[1];
                nombre[0] = jetN.getText().toString();
                id = new String[1];
                id[0] = jetI.getText().toString();

                Intent itn;
                Bundle bdl;

                if(!jetN.getText().toString().isEmpty() && !jetI.getText().toString().isEmpty())
                {
                    try
                    {
                        Cursor c = sqld.rawQuery("SELECT * FROM Contactos WHERE nombre = ?", nombre);
                        itn = new Intent(MainActivity.this, cambioNombre.class); //ligadura del intent con las clases que se usarán
                        bdl = new Bundle();    //Declaración de un nuevo conjunto
                        bdl.putString("NOMBRE", nombre[0]);   //Cadena de texto de EDitText agregada al conjunto con id
                        bdl.putString("ID", id[0]);
                        itn.putExtras(bdl);        //Envío del conjunto a la otra clase
                        startActivity(itn);         //Inicio de clase para iniciar su función
                    }
                    catch(Exception e)
                    {
                        jtvL.setText(String.valueOf(e) + "\nPosiblemente el Nombre no está registrado");
                    }
                }
                else if (jetN.getText().toString().isEmpty())
                {
                    try
                    {
                        Cursor c = sqld.rawQuery("SELECT * FROM Contactos WHERE id = ?", id);
                        itn = new Intent(MainActivity.this, cambioNombre.class); //ligadura del intent con las clases que se usarán
                        bdl = new Bundle();    //Declaración de un nuevo conjunto
                        bdl.putString("NOMBRE", nombre[0]);   //Cadena de texto de EDitText agregada al conjunto con id
                        bdl.putString("ID", id[0]);
                        itn.putExtras(bdl);        //Envío del conjunto a la otra clase
                        startActivity(itn);         //Inicio de clase para iniciar su función
                    }
                    catch(Exception e)
                    {
                        jtvL.setText(String.valueOf(e) + "\nPosiblemente el ID no está registrado");
                    }
                }
            }
        });

        jbnB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] id, nombre;
                nombre = new String[1];
                nombre[0] = jetN.getText().toString();
                id = new String[1];
                id[0] = jetI.getText().toString();

                if(!jetN.getText().toString().isEmpty() && !jetI.getText().toString().isEmpty())
                {
                    try
                    {
                        sqld.delete("Contactos", "nombre=?",nombre);
                        jtvL.setText("El archivo se borró existosamente");
                    }
                    catch(Exception e)
                    {
                        jtvL.setText(String.valueOf(e) + "\nPosiblemente el Nombre no está registrado");
                    }

                }
                else if (jetN.getText().toString().isEmpty())
                {
                    try
                    {
                        sqld.delete("Contactos", "id=?", id);
                        jtvL.setText("El registro se borró existosamente");
                    }
                    catch(Exception e)
                    {
                        jtvL.setText(String.valueOf(e) + "\nPosiblemente el ID no está registrado");
                    }
                }
            }
        });

    }
}