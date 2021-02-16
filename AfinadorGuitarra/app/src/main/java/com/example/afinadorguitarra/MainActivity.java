package com.example.afinadorguitarra;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private static final String tocando = "Reproduciendo: ";
    private MediaPlayer player;
    private Button bnE, bnB, bnG, bnD, bnA, bnE2, salir;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnE = (Button) this.findViewById(R.id.cuerdaE);
        bnE.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(1);

            }
        });
        bnB = (Button) this.findViewById(R.id.cuerdaB);
        bnB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(2);

            }
        });
        bnG = (Button) this.findViewById(R.id.cuerdaG);
        bnG.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(3);

            }
        });
        bnD = (Button) this.findViewById(R.id.cuerdaD);
        bnD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(4);

            }
        });
        bnA = (Button) this.findViewById(R.id.cuerdaA);
        bnA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(5);

            }
        });
        bnE2 = (Button)this.findViewById(R.id.cuerdaE1);
        bnE2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                playSound(6);

            }
        });
        salir = (Button) this.findViewById(R.id.salir);
        salir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                MainActivity.this.finish();

            }
        });
        tv = (TextView) this.findViewById(R.id.tv);

    }

    @Override
    public void onPause() {
        try {
            super.onPause();
            player.pause();
        } catch (Exception e) {}

    }
    public void onStop() {
        super.onStop();
        player.stop();

    }
    private void playSound(int arg) {
        try {
            if (player.isPlaying()) {
                player.stop();
                player.release();

            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        if (arg == 1) {
            player = MediaPlayer.create(this, R.raw.e1);
            tv.setText(tocando + "Cuerda E");
        } else if (arg == 2) {
            player = MediaPlayer.create(this, R.raw.b2);
            tv.setText(tocando + "Cuerda B");
        } else if (arg == 3) {
            player = MediaPlayer.create(this, R.raw.g3);
            tv.setText(tocando + "Cuerda G");
        } else if (arg == 4) {
            player = MediaPlayer.create(this, R.raw.d4);
            tv.setText(tocando + "Cuerda D");
        } else if (arg == 5) {
            player = MediaPlayer.create(this, R.raw.a5);
            tv.setText(tocando + "Cuerda A");
        } else if (arg == 6) {
            player = MediaPlayer.create(this, R.raw.e6);
            tv.setText(tocando + "Cuerda E");
        }
        player.setLooping(true);
        player.start();
    }


}