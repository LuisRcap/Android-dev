package com.example.examen1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText jet[] = new EditText[9];
    Button jbn1;
    Bundle bdl;
    Intent itn;
    TextView jtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int matriz[][] = new int[3][3];

        jet[0] = (EditText) findViewById(R.id.xet1);
        jet[1] = (EditText) findViewById(R.id.xet2);
        jet[2] = (EditText) findViewById(R.id.xet3);
        jet[3] = (EditText) findViewById(R.id.xet4);
        jet[4] = (EditText) findViewById(R.id.xet5);
        jet[5] = (EditText) findViewById(R.id.xet6);
        jet[6] = (EditText) findViewById(R.id.xet7);
        jet[7] = (EditText) findViewById(R.id.xet8);
        jet[8] = (EditText) findViewById(R.id.xet9);

        jtv = (TextView) findViewById(R.id.xtv10);

        jbn1 = (Button) findViewById(R.id.btn1);

        jbn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itn = new Intent(MainActivity.this, FiguraActivity.class);
                bdl = new Bundle();
                boolean datos = true;
                int j = 0, k = 0;
                for(int i = 0; i < 9; i++)
                {
                    if (!jet[i].getText().toString().isEmpty())
                    {
                        matriz[j][k] = Integer.parseInt(jet[i].getText().toString());
                        k++;
                        if(k == 3)
                        {
                            k = 0;
                            j++;
                        }
                    }
                    else
                    {
                        datos = false;
                    }
                }

                if(datos)
                {
                    bdl.putIntArray("VECTOR1", matriz[0]);
                    bdl.putIntArray("VECTOR2", matriz[1]);
                    bdl.putIntArray("VECTOR3", matriz[2]);

                    itn.putExtras(bdl);

                    startActivity(itn);
                }
                else
                {
                    jtv.append("\n\nUno o varios campos están vacíos\nPara crear la firgura todos los vectores deben estar completos");
                }

            }
        });


    }
}