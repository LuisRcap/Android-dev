package com.example.fragmentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration c = getResources().getConfiguration();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(c.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            HorizontalFragmento hf = new HorizontalFragmento();
            ft.replace(android.R.id.content, hf);
        }else{
            VerticalFragmento vf = new VerticalFragmento();
            ft.replace(android.R.id.content, vf);
        }
        ft.commit();
    }
}