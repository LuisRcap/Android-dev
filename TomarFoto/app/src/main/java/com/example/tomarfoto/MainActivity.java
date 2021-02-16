package com.example.tomarfoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn, btnGaleria;
    ImageView vi;
    String ruta;
    Bundle bdl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCamara);
        vi = findViewById(R.id.idView);
        btnGaleria =  findViewById(R.id.Galeria);

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdl = new Bundle();
                Intent j = new Intent(MainActivity.this,LienzoActivity.class);
                startActivity(j);
            }
        });

    }

    public void abrirCamara(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if(i.resolveActivity(getPackageManager())!=null){

        File imagenArchivo = null;
        try {
            imagenArchivo = crearImagen();
        } catch (IOException ex) {
            Log.e("error", ex.toString());
        }

        if (imagenArchivo != null) {
            Uri fotoUri = FileProvider.getUriForFile(this, "com.example.tomarfoto.fileprovider", imagenArchivo);
            i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
            startActivityForResult(i, 1);
        }

        startActivityForResult(i, 1);
        //}

        /*
        int id=getResources().getIdentifier(ruta, "drawable", getPackageName());
        Drawable imagenn = getResources().getDrawable(id);
        */

    }

    protected void onActivityResult(int RequestCode, int ResultCode, Intent data) {
        super.onActivityResult(RequestCode, ResultCode, data);
        if (RequestCode == 1 && ResultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            Bitmap imgBitmap = BitmapFactory.decodeFile(ruta);
            vi.setImageBitmap(imgBitmap);
        }
    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        ruta = imagen.getAbsolutePath();
        return imagen;
    }

}

