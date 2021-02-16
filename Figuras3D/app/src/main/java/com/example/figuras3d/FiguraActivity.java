package com.example.figuras3d;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FiguraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        Figura figura = new Figura(FiguraActivity.this, bundle.getString("FIGURA"));
        setContentView(figura);

    }
}
