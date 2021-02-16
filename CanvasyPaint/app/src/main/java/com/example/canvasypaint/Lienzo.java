package com.example.canvasypaint;

import android.content.*;
import android.graphics.*;
import android.view.View;

public class Lienzo extends View{
    Paint p;
    int x, y;
    public Lienzo(Context c){
        super(c);
    }

    protected void onDraw(Canvas c){
        super.onDraw(c); // Canvas pinta atributos
        p = new Paint(); // Paint asigna atributos
        x = c.getWidth(); // También: getMeasuredWidth() o getRight(), x=480
        y = c.getHeight(); // También: getMeasuredHeight() o getBottom(), y=762
        p.setColor(Color.WHITE); // Fondo blanco
        c.drawPaint(p);
        p.setColor(Color.BLACK); // Texto negro
        p.setTextSize(20);
        c.drawText("x0=" + x/2 + ", y0=" + y/2, x/2 + 20, y/2-20, p);
        p.setColor(Color.rgb(0, 0, 255)); // Ejes azules
        c.drawLine(x/2, 0, x/2, y, p);
        c.drawLine(0, y/2, x, y/2, p);
        c.drawLine(0, y, x, 0, p);

        p.setTextAlign(Paint.Align.RIGHT);
        p.setTypeface(Typeface.MONOSPACE);
        c.drawText("Eje X", x-5, y/2-10, p);
        p.setTextAlign(Paint.Align.CENTER);
        p.setTypeface(Typeface.DEFAULT_BOLD);
        c.drawText("Eje Y", x/2+30, 20, p);

        p.setColor(Color.argb(236, 7, 13, 89));
        c.drawOval(60, 140, 300, 420, p);

        p.setColor(Color.argb(236, 207, 117, 0));
        c.drawRect(420, 140, 660, 420, p);

        p.setColor(Color.argb(236, 236, 1, 1));
        c.drawRoundRect(420, 700, 660, 980, 50, 50, p);

        p.setColor(Color.argb(236, 18, 202, 214));
        c.drawArc(60, 700, 300, 980, 0, 270, true, p);
    }

}
