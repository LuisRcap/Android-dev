package com.example.fragmentolistado;

import androidx.appcompat.app.AppCompatDialogFragment;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

public class FragmentDetalle extends AppCompatDialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }
    public void mostrarDetalle(String texto) {
        TextView tv = (TextView)getView().findViewById(R.id.xtvDetalle);
        tv.setText(texto);
    }

}
