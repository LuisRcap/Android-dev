package com.example.aplicacionedittext;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    TextView jtv1;
    EditText jet1;
    int x = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAceptar = (Button) findViewById(R.id.btnAcept);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        jtv1 = (TextView) findViewById(R.id.xtv1);
        jet1 = (EditText) findViewById(R.id.xet1);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jtv1.setText("Hola "+ jet1.getText().toString());
            }
        });




    }
}