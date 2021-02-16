package com.example.camara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.*;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;
import android.os.*;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String ruta =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                    "/misfotos/";
    private File f = new File(ruta);
    private Button jbn;
    TextView jtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jbn = (Button) findViewById(R.id.xbn);
        jtv = (TextView) findViewById(R.id.xtv);
        f.mkdirs();
        jbn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s = ruta + getCode() + ".jpg";
                File f1 = new File(s);
                try{
                    f1.createNewFile();
                }catch(IOException ex){
                    Log.e("Error", "Error:" + ex);
                }
                try {
                    Uri u = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                            BuildConfig.APPLICATION_ID + ".provider", f1);
                    Intent in = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    in.putExtra(MediaStore.EXTRA_OUTPUT, u);
                    startActivityForResult(in, 0);
                }
                catch (Exception e)
                {
                    jtv.append("\n\nError: " + String.valueOf(e));
                }

            }
        });
    }

    private String getCode(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhmmss");
        String n = "pic_" + sdf.format(new Date());;
        return n;
    }

}