package com.example.clase_imagen_angulo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    int TAKE_PICTURE = 1,code;
    TextView jtv1;
    ImageView img;
    Intent i;
    Bundle bdl;
    Bitmap map;
    Button btnFoto,btnGaleria;
    final int COD_FOTO = 20;
    final String CARPETA_RAIZ = "MisFotosApp";
    final String CARPETA_IMAGENES = "imagenes";
    final String RUTA_IMAGEN = CARPETA_RAIZ + CARPETA_IMAGENES;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jtv1 = (TextView) findViewById(R.id.xtv1);
        btnGaleria = (Button) findViewById(R.id.Galeria);
        img= (ImageView) findViewById(R.id.LV);
        btnFoto= (Button) findViewById(R.id.Foto);
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new
                        String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
            btnFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TomarFoto();
                }
            });
        }
        catch (Exception e)
        {
            jtv1.append("\n\n"+ String.valueOf(e));
        }

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bdl = new Bundle();
                Intent j = new Intent(MainActivity.this,LienzoActivity.class);
                startActivity(j);
            }
        });



    }
    public void TomarFoto() {
        String nombreImagen = "";
        File fileImagen = new File(Environment.getExternalStorageDirectory(), RUTA_IMAGEN);
        boolean isCreada = fileImagen.exists();
        if(isCreada == false) {
            isCreada = fileImagen.mkdirs();
        }
        if(isCreada == true) {
            nombreImagen = (System.currentTimeMillis() / 1000) + ".jpg";
        }
        path =
                Environment.getExternalStorageDirectory()+File.separator+RUTA_IMAGEN+File.separator+nombreImagen;
        File imagen = new File(path);
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String authorities = this.getPackageName()+".provider";
            Uri imageUri = FileProvider.getUriForFile(this, authorities, imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        startActivityForResult(intent, COD_FOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new
                            MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                }
                            });
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    img.setImageBitmap(bitmap);
                    break;
            }
        }
    }

}