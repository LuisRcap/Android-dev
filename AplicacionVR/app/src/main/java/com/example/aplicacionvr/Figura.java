package com.example.aplicacionvr;

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
    Obj obj, obj2;
    String figura;
    double X;
    double Y;

    public Figura(Context context, String fig) {
        super(context);
        figura = fig;
        obj = new Obj(figura);
        paint = new Paint();
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
        paint.setColor(Color.BLUE);
        centerX = (2*maxX);
        centerY = (2*maxY);
    }
    int iX(float x){
        return Math.round(centerX + x);
    }
    int iY(float y){
        return Math.round(centerY - y);
    }
    void line(Canvas canvas, int i, int j){
        Point2D p = obj.vScr[i], q = obj.vScr[j];
        canvas.drawLine(iX(p.x), iY(p.y), iX(q.x), iY(q.y), paint);
    }
    public void  onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        maxX = getWidth() - 1;
        maxY = getHeight() - 1;
        minMaxXY = Math.min(maxX, maxY);
        obj.d = obj.rho*minMaxXY/obj.objSize;
        obj.eyeAndScreen();

        if(figura.equals("Cubo3D")){
            line(canvas, 0, 1); line(canvas, 1, 2); line(canvas, 2, 3); line(canvas, 3, 0); // aristas horizontales inferiores
            line(canvas, 4, 5); line(canvas, 5, 6); line(canvas, 6, 7); line(canvas, 7, 4); // aristas horizontales superiores
            line(canvas, 0, 4); line(canvas, 1, 5); line(canvas, 2, 6); line(canvas, 3, 7); // aristas verticales
        }
        else if(figura.equals("Piramide3D")){
            line(canvas, 0, 1); line(canvas, 1, 2); line(canvas, 2, 3); line(canvas, 3, 0); // aristas horizontales
            line(canvas, 0, 4); line(canvas, 1, 4); line(canvas, 2, 4); line(canvas, 3, 4); // aristas verticales
        }
        else if(figura.equals("Prisma3D")){
            line(canvas, 0, 1); line(canvas, 1, 2); line(canvas, 2, 3); line(canvas, 3, 0); // aristas horizontales inferiores
            line(canvas, 4, 5); line(canvas, 5, 6); line(canvas, 6, 7); line(canvas, 7, 4); // aristas horizontales superiores
            line(canvas, 0, 4); line(canvas, 1, 5); line(canvas, 2, 6); line(canvas, 3, 7); // aristas verticales
        }
        else if(figura.equals("Cono3D")){
            for (int i= 0; i < 71; i++){
                line(canvas, i, i+1);
                line(canvas, i, 72);
            }
            line(canvas, 71, 0);
            line(canvas, 71, 72);
        }
        else if(figura.equals("Esfera3D")){
            for (int k = 0; k < 4608; k +=36) {
                for (int i = 0; i < 35; i+=1){
                    line(canvas, i+k, i+1+k);
                }
                line(canvas, k+35, k);
            }
        }
        else if(figura.equals("Cilindro3D")){
            for (int i = 0; i < 142; i+=1){
                line(canvas, i, i+2);
                if (i% 2 == 0)
                    line(canvas, i, i+1);
            }
            line(canvas, 142, 143);
            line(canvas, 142, 0);
            line(canvas, 143, 1);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            s = -1;
            X = motionEvent.getX();
            Y = motionEvent.getY();
            for (int i = 0; i < 2; i++) {
                double dx = X - centerX, dy = Y - centerY;
                double d = (double) Math.sqrt(dx * dx + dy * dy);
                if (d <= 75) {
                    s = i;
                    invalidate();
                }
            }
        }

        if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
            if(s > -1 ){
                centerX = (int) motionEvent.getX();
                centerY = (int) motionEvent.getY();
                invalidate();
            }
        }

        if(motionEvent.getX() < X){
            obj.theta = (float) (obj.theta - 2*Math.PI/300);
        }
        if(motionEvent.getX() > X){
            obj.theta = (float) (obj.theta + 2*Math.PI/300);
        }
        if(motionEvent.getY() < Y){
            obj.phi = (float) (obj.phi - 2*Math.PI/400);
        }
        if(motionEvent.getY() > Y ){
            obj.phi = (float) (obj.phi + 2*Math.PI/400);
        }
        invalidate();
        return true;
    }

}
