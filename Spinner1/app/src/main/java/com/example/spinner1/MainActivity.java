package com.example.spinner1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {

    Spinner s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = (Spinner) findViewById(R.id.xsp);
        s.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> av, View v, int i, long l){
                Toast.makeText(MainActivity.this, s.getSelectedItem().toString(),
                        Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> arg0){ }
        });
    }
}