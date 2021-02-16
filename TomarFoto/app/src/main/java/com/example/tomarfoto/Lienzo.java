package com.example.tomarfoto;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
//import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;

public class Lienzo extends View {
    Bitmap background;
    //MainActivity ma = new MainActivity();
    DecimalFormat df = new DecimalFormat("#.00");
    Paint p, q, r;
    int x, y, code;
    float auxX;
    double la, lb, lc;
    float auxY;
    float[] PX = new float[5];
    float[] PY = new float[5];
    int cont = 0; //para verificar que sea le primer punto
    String accion = "accion";
    Path path = new Path();

    public Lienzo(Context c, Bitmap bitmap) {
        super(c);
        background = bitmap;
    }

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas c) {
        //c.drawColor(Color.YELLOW);//fondo

        super.onDraw(c);
        Display display;
        Rect screen;
        int ancho, alto;
        Point size = new Point();
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        ancho = size.x;
        alto = size.y;
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha(180);
        screen = new Rect((ancho)/8, (alto)/8, (ancho*7)/8, (alto*7)/8);
        c.drawBitmap(background, null, screen, alphaPaint);
        p=new Paint();
        p.setAntiAlias(true);


        x = c.getWidth();
        y = c.getHeight();

        q = new Paint();
        q.setStrokeWidth(7);
        q.setColor(Color.BLUE);
        q.setTextSize(50);


        /*
        IMAGEN GUARDADA EN CARPETA DRAWABLE
        imagen.setBounds(0,0,imagen.getIntrinsicWidth(), imagen.getIntrinsicHeight());//imagen coordenadas posicion
        imagen.draw(c);//pintar imagen
        */

        //p.setStrokeWidth(5);
        //p.setColor(Color.WHITE); // Fondo blanco
        //c.drawPaint(p);

        p.setColor(Color.GRAY); // Texto gris
        p.setTextSize(40); // Tamaño de texto
        p.setStyle(Paint.Style.STROKE);

        c.drawText("Dibuje tres puntos en la pantalla:", 25 , 55, p);

        //dibujar si toca la pantalla
        if (accion.equals("up") && cont < 3){ //para que solo dibuje cuatro puntos
            if(cont == 0){
                path.moveTo(auxX,auxY);     //necesario para que el primer punto sea donde primero toque el usuario
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
            //nombre de los vertices
            c.drawText("v0 ", PX[0]-30, PY[0]+30, q);
            c.drawText("v1 ", PX[1] , PY[1], q);
            c.drawText("v2 ", PX[2] , PY[2], q);
            //angulo
            double m2 = (PY[1]-PY[0]) / (PX[1]-PX[0]); //pendiente m2
            double m1 = (PY[2]-PY[0]) / (PX[2]-PX[0]); //pendiente m1
            double numerador = m2 - m1;
            double denominador = 1 + m1*m2;
            double radio = numerador/denominador;
            double angleRad = Math.atan(radio); // retorna el arcotangente (en radianes) de un numbero
            double angleDeg = (angleRad*180)/Math.PI; //convercion a grados
            if(PY[1]<PY[2]) {//quiere decir que la pendiente cambia v0-v1 = m2
                double m11 = (PY[1]-PY[0]) / (PX[1]-PX[0]); //pendiente m1
                double m22 = (PY[2]-PY[0]) / (PX[2]-PX[0]); //pendiente m2
                double numeradorr = m22 - m11;
                double denominadorr = 1 + m11*m22;
                double radioo = numeradorr/denominadorr;
                double angleRadd = Math.atan(radioo); // retorna el arcotangente (en radianes) de un numbero
                double angleDegg = (angleRadd*180)/Math.PI; //convercion a grados
                if(angleDegg<0)
                    angleDegg = angleDegg+180;
                c.drawText("Angulo del vertice v0: " + df.format(angleDegg), 20 , 110, p);
            }else{
                if(angleDeg<0)
                    angleDeg = angleDeg+180;
                c.drawText("Angulo del vertice v0: " + df.format(angleDeg), 20 , 110, p);
            }
        }
        c.drawPath(path, p);
    }

    public boolean onTouchEvent(MotionEvent e) {
        auxX = e.getX();
        auxY = e.getY();
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            accion = "down";
        }
        if (e.getAction() == MotionEvent.ACTION_UP)
            accion = "up";
        invalidate();
        return true;
    }
}