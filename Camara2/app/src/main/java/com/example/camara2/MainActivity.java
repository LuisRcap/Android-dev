package com.example.camara2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.*;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.*;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.File;


public class MainActivity extends AppCompatActivity {


    ImageView ivFoto;
    Button btnTomarFoto;
    final int COD_FOTO = 20;
    final String CARPETA_RAIZ = "MisFotosApp";
    final String CARPETA_IMAGENES = "imagenes";
    final String RUTA_IMAGEN = CARPETA_RAIZ + CARPETA_IMAGENES;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivFoto = findViewById(R.id.ivFoto);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new
                    String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TomarFoto();
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
                    ivFoto.setImageBitmap(bitmap);
                    break;
            }
        }
    }


}