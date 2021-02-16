package com.example.examen1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Figura extends View {
    Paint paint = new Paint();
    Paint p;
    int s = -1;
    int centerX, centerY, maxX, maxY, minMaxXY;
    Obj obj;
    String figura;
    double X;
    double Y;
    int volumen;

    public Figura(Context context, int vector1[], int vector2[], int vector3[]) {
        super(context);
        obj = new Obj(vector1, vector2, vector3);
        paint = new Paint();
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
        paint.setColor(Color.BLUE);

        volumen = vector3[0]*vector2[1]*vector1[2];
        volumen += vector3[1]*vector2[2]*vector1[0];
        volumen += vector3[2]*vector2[0]*vector1[1];
        volumen -= vector1[0]*vector2[1]*vector3[2];
        volumen -= vector2[0]*vector3[1]*vector1[2];
        volumen -= vector3[0]*vector1[1]*vector2[2];

        if(volumen < 0)
            volumen = -1*volumen;


    }

    int iX(float x) {
        return Math.round(centerX + x);
    }

    int iY(float y) {
        return Math.round(centerY - y);
    }

    void line(Canvas canvas, int i, int j) {
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        canvas.drawLine(iX(p.x), iY(p.y), iX(q.x), iY(q.y), paint);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        maxX = canvas.getWidth() - 1;
        maxY = canvas.getHeight() - 1;
        centerX = (maxX+1)/2;
        centerY = (maxY+1)/2;

        paint.setColor(Color.BLACK); // Texto negro
        paint.setTextSize(20);
        canvas.drawText("Volumen = " + volumen, 20, 20, paint);

        canvas.drawText("Eje X ", maxX-60, centerY-20, paint);
        canvas.drawText("Eje Y ", centerX+20, 30, paint);
        canvas.drawText("Eje Z ", maxX-60, (maxY+1)*26/125 -15, paint);

        canvas.drawLine(centerX, 0, centerX, maxY+1, paint);
        canvas.drawLine(0, centerY, maxX, centerY, paint);
        canvas.drawLine(0, (maxY+1)-(maxY+1)*26/125, maxX+1,  (maxY+1)*26/125, paint);
        paint.setColor(Color.BLUE);

        minMaxXY = Math.min(maxX, maxY);
        obj.d = obj.rho * minMaxXY / obj.objSize;
        obj.eyeAndScreen();


        line(canvas, 0, 1); line(canvas, 1, 2); line(canvas, 2, 3); line(canvas, 3, 0); // aristas horizontales inferiores
        line(canvas, 4, 5); line(canvas, 5, 6); line(canvas, 6, 7); line(canvas, 7, 4); // aristas horizontales superiores
        line(canvas, 0, 4); line(canvas, 1, 5); line(canvas, 2, 6); line(canvas, 3, 7); // aristas verticales

    }


}
