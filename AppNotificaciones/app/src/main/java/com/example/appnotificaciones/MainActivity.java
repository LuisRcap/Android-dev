package com.example.appnotificaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.*;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.graphics.drawable.BitmapDrawable;
import androidx.core.app.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int t=200, i=0;
    boolean c=true;
    TextView jtv;
    Button jbnN;
    private static final int NOTIF_ALERTA_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jtv = (TextView) findViewById(R.id.xtv);
        jbnN = (Button)findViewById(R.id.xbnN);

        jbnN.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                    @SuppressLint("UseCompatLoadingForDrawables") NotificationCompat.Builder ncb =
                            new NotificationCompat.Builder(MainActivity.this)
                                    .setSmallIcon(android.R.drawable.stat_sys_warning)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                                            R.drawable.ic_launcher_foreground))
                                    .setContentTitle("Alerta de Notificación")
                                    .setContentText("Uso de la notificación." + "i=" + ++i)
                                    .setContentInfo("Un valor")
                                    .setTicker("Mensaje de Alerta!");

                    Intent in = new Intent(MainActivity.this, MainActivity.class);
                    PendingIntent pi = PendingIntent.getActivity(MainActivity.this,0,in,0);
                    ncb.setContentIntent(pi);
                    NotificationManager nm = (NotificationManager) getSystemService(
                            Context.NOTIFICATION_SERVICE);
                    nm.notify(NOTIF_ALERTA_ID, ncb.build());
                    jtv.setText("Cuenta: i=" + i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}