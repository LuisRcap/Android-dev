package com.example.examen1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FiguraActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();



        Figura figura = new Figura(FiguraActivity.this, bundle.getIntArray("VECTOR1"), bundle.getIntArray("VECTOR2"), bundle.getIntArray("VECTOR3"));
        setContentView(figura);

    }
}
