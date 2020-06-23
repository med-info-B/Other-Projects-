package com.example.tp3;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.jar.Attributes;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

class Point {
    public float x, y;
    public int color;
    public float epesseur;

    public Point(float x , float y, int color, float epesseur){
        this.x = x;
        this.y = y ;
        this.color = color;
        this.epesseur = epesseur;
    }
}
public class Dessin extends View {
    ArrayList<Point> points;
    float epsseur  = 10;
    int color = Color.BLUE;

    public Dessin(Context context, AttributeSet atts){
        super(context, atts);
        points = new ArrayList<>();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point(event.getX(),event.getY(), color, epsseur));
        invalidate ();
        return true;

    }

    public void setEpsseur(float x){
        this.epsseur = x;
    }
    public void setColor(int color){
        this.color = color;
    }
    @Override
    protected void onDraw ( Canvas canvas ) {
        for (Point point : points){
            Paint paint = new Paint();
            paint.setColor(point.color);
            paint.setStrokeWidth(point.epesseur);
            canvas.drawPoint(point.x,point.y,paint);
        }
    }





}
