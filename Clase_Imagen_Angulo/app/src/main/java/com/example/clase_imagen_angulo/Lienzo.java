package com.example.clase_imagen_angulo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Lienzo extends View {
    Bitmap background;
    //MainActivity ma = new MainActivity();
    DecimalFormat df = new DecimalFormat("#.00");
    Paint p,q,r;
    int x, y,code;
    float auxX;
    double la,lb,lc;
    float auxY;
    float[] PX = new float[5];
    float[] PY = new float[5];
    int cont = 0; //para verificar que sea el primer punto
    String accion = "accion";
    Path path = new Path();
    public Lienzo(Context c,Bitmap bitmap){
        super(c);
        background=bitmap;
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        Display display;
        Rect screen;
        int ancho, alto;
        Point size = new Point();
        //background = BitmapFactory.decodeResource(getResources(),R.drawable.es_logo);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        ancho = size.x;
        alto = size.y;
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(180);
        screen = new Rect((ancho)/8, (alto)/8, (ancho*7)/8, (alto*7)/8);
        c.drawBitmap(background, null, screen, alphaPaint);
        p = new Paint(); // Paint asigna atributos
        p.setAntiAlias(true);

        x = c.getWidth();
        y = c.getHeight();

        q = new Paint();
        q.setStrokeWidth(7);
        q.setColor(Color.BLUE);
        q.setStyle(Paint.Style.STROKE);
        q.setTextSize(50);


        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8);
        p.setColor(Color.WHITE);
        //c.drawPaint(p);
        p.setColor(Color.BLACK);
        p.setTextSize(40);

        r = new Paint();
        r.setStrokeWidth(4);
        r.setTextSize(60);
        r.setColor(Color.BLACK);

        c.drawText("Cree 3 puntos", 20 , 60, r);
        //dibujar si toca la pantalla
        if (accion.equals("up") && cont < 3){
            if(cont == 0){
                path.moveTo(auxX,auxY);
                PX[cont] = auxX;
                PY[cont] = auxY;
                cont = cont + 1;
            }
            else{
                    PX[cont] = auxX;
                    PY[cont] = auxY;
                if (cont==1){
                    path.lineTo(PX[1],PY[1]);
                    cont = cont + 1;
                }else {
                    path.moveTo(auxX,auxY);
                    cont = cont + 1;
                }

            }
        }
        if (cont == 3){
            path.lineTo(PX[0],PY[0]);
            la = Math.sqrt( Math.abs(Math.pow((PX[1]-PX[0]),2) + Math.pow((PY[1]-PY[0]),2)) )/100;//v1-v0
            lb = Math.sqrt( Math.abs(Math.pow((PX[2]-PX[1]),2) + Math.pow((PY[2]-PY[1]),2)) )/100;//v1-v2
            lc = Math.sqrt( Math.abs(Math.pow((PX[2]-PX[0]),2) + Math.pow((PY[2]-PY[0]),2)) )/100; //v0-v2

            //nombre de los vertices
            c.drawText("Va ", PX[0]+10 , PY[0]+10, q);
            c.drawText("Vb ", PX[1]+10 , PY[1]+10, q);
            c.drawText("Vc ", PX[2]+10 , PY[2]+10, q);

            //angulo
            double num,den,anguloa;
            float angulob,anguloc;
            //a
            num = (lb*lb)-(la*la)-(lc*lc);
            den = (-2)*la*lc;
            anguloa =Math.toDegrees(Math.acos(num/den));
            c.drawText("Angulo Va: " + df.format(anguloa) , 20 , 120, r);
        }
        System.out.println("*****************************\nCount = "+cont);
            c.drawPath(path, p);
    }
    public boolean onTouchEvent(MotionEvent e){
        auxX = e.getX();
        auxY = e.getY();
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            accion = "down";
        }
        if (e.getAction() == MotionEvent.ACTION_UP)
            accion = "up";
        invalidate();
        return true;
    }
}