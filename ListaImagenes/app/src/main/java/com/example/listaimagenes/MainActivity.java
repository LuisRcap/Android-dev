package com.example.listaimagenes;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.listado);
        ArrayList<ListaEntrada> al = new ArrayList<ListaEntrada>();
        al.add(new ListaEntrada(R.drawable.buho, "COREI3", "Procesador de décima generación de Intel Core I3 10100 de 4 núcleos, 8 hilos de trabajo, con frecuencia máxima de 4.3GHz, litografía de 14nm, 6Mb de caché y TDP de 65W."));
        al.add( new ListaEntrada(R.drawable.colibri, "RYZEN3", "Procesador AMD 3er generación de Ryzen 3 3100 de 4 núcleos, 8 hilos, con frecuencia máxima de 3.9GHz, litografía de 7nm, caché L1 de 256Kb, L2 2Mb y L3 de 16Mb con un TDP de 65W."));
        al.add(new ListaEntrada(R.drawable.cuervo, "COREI5", "Procesador de décima generación de Intel Core I5 10600K de 6 núcleos, 12 hilos, con frecuencia máxima de 4.8GHz, litografía de 14nm, 12Mb de caché y TDP de 125W."));
        al.add(new ListaEntrada(R.drawable.flamenco, "RYZEN5", "Procesador AMD 3er generación de Ryzen 5 3600X de 6 núcleos, 12 hilos, con frecuencia máxima de 4.4GHz, litografía de 7nm, caché L1 de 384Kb, L2 3Mb y L3 de 32Mb con un TDP de 95W."));
        al.add(new ListaEntrada(R.drawable.kiwi, "COREI7", "Procesador de décima generación de Intel Core I7 10700K de 8 núcleos, 16 hilos, con frecuencia máxima de 5.1GHz, litografía de 14nm, 16Mb de caché y TDP de 125W."));
        al.add(new ListaEntrada(R.drawable.loro, "RYZEN7", "Procesador AMD 3er generación de Ryzen 7 3800X de 8 núcleos, 16 hilos, con frecuencia máxima de 4.5GHz, litografía de 7nm, caché L1 de 512Kb, L2 4Mb y L3 de 32Mb con un TDP de 105W."));
        al.add(new ListaEntrada(R.drawable.pavo, "COREI9", "Procesador de décima generación de Intel Core I9 10900k de 10 núcleos, 20 hilos, con frecuencia máxima de 5.3GHz, litografía de 14nm, caché de 20Mb y TDP de 125W."));
        al.add(new ListaEntrada(R.drawable.pinguino, "RYZEN9", "Procesador AMD 3er generación de Ryzen 9 3900X de 12 núcleos, 24 hilos, con frecuencia máxima de 4.6GHz, litografía de 7nm, caché L1 de 768kb, L2 6Mb y L3 de 64Mb."));

        lv = (ListView) findViewById(R.id.ListView_listado);

        lv.setAdapter(new ListaAdapter(this, R.layout.activity_main, al){
            public void onEntrada(Object o, View v) {
                if (o != null) {
                    TextView texto_superior_entrada = (TextView) v.findViewById(R.id.textView_superior);

                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((ListaEntrada) o).get_textoEncima());

                    TextView texto_inferior_entrada = (TextView) v.findViewById(R.id.textView_inferior);

                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((ListaEntrada) o).get_textoDebajo());

                    ImageView imagen_entrada = (ImageView) v.findViewById(R.id.imageView_imagen);

                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((ListaEntrada) o).get_idImagen());
                }
            }
        });

        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                ListaEntrada le = (ListaEntrada) av.getItemAtPosition(i);
                CharSequence cs = "Seleccionado: " + le.get_textoDebajo();
                Toast t = Toast.makeText(MainActivity.this, cs, Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }

}
