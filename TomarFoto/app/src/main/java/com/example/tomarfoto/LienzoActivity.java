package com.example.tomarfoto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LienzoActivity extends Activity {
    int SELECT_PICTURE = 2;
    Intent i;
    int code;
    Bitmap map;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        code = SELECT_PICTURE;
        startActivityForResult(i, code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE){
            Uri image = data.getData();
            InputStream is; try {
                is = getContentResolver().openInputStream(image);
                BufferedInputStream bis = new BufferedInputStream(is);
                map = BitmapFactory.decodeStream(bis);


                Lienzo l = new Lienzo(this,map);
                setContentView(l);


            } catch (FileNotFoundException e) {
                Intent j= new Intent(this,MainActivity.class);
                startActivity(j);
            }
        }
    }
}
