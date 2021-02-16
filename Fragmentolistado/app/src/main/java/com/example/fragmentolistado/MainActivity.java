package com.example.fragmentolistado;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements FragmentListado.GruposListener {

    FragmentListado fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl =
                (FragmentListado)getSupportFragmentManager().findFragmentById(R.id.FrgListado);
        fl.setGruposListener(this);
    }
    @Override
    public void onGrupoSeleccionado(Grupo c) {
        boolean bo = (getSupportFragmentManager().findFragmentById(R.id.FrgDetalle) !=
                null);
        if(bo) {
            ((FragmentDetalle) getSupportFragmentManager().findFragmentById(
                    R.id.FrgDetalle) ).mostrarDetalle( c.getTexto() );
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, c.getTexto());
            startActivity(i);
        }
    }
}